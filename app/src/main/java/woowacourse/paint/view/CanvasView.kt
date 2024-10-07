package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.R
import woowacourse.paint.model.BrushState
import woowacourse.paint.model.CircleState
import woowacourse.paint.model.DrawingToolType
import woowacourse.paint.model.EraserState
import woowacourse.paint.model.PenState
import woowacourse.paint.model.RectangularState
import woowacourse.paint.model.Strokes

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val strokes: Strokes = Strokes()
    private var currentState: BrushState = PenState(strokes)
    private var currentPath: Path? = null
    private val currentPaint: Paint = Paint()

    private var cacheBitmap: Bitmap? = null
    private var cacheCanvas: Canvas? = null

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        initializePaint(context, attrs)
    }

    private fun initializePaint(
        context: Context,
        attrs: AttributeSet,
    ) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0,
            0,
        ).apply {
            initializePaintAttributes()
        }
    }

    private fun TypedArray.initializePaintAttributes() {
        try {
            currentPaint.apply {
                style = Paint.Style.STROKE
                isAntiAlias = true
                strokeJoin = Paint.Join.ROUND
                strokeCap = Paint.Cap.ROUND
                color = getColor(R.styleable.CustomView_lineColor, DEFAULT_LINE_COLOR)
                strokeWidth = getDimension(R.styleable.CustomView_lineWidth, DEFAULT_LINE_WIDTH)
            }
        } finally {
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        cacheBitmap?.let { canvas.drawBitmap(it, 0f, 0f, null) }
        currentPath?.let { currentState.draw(canvas, it, currentPaint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = currentState.start(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath?.let { currentState.move(it, pointX, pointY) }
            }

            MotionEvent.ACTION_UP -> {
                currentPath?.let {
                    currentState.finish(it, currentPaint)
                    updateCache()
                }
                currentPath = null
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setLineColor(color: Int) {
        currentPaint.color = color
    }

    fun setLineWidth(width: Float) {
        currentPaint.strokeWidth = width
    }

    fun setDrawingToolType(drawingToolType: DrawingToolType) {
        setCurrentState(drawingToolType)
        setEraseMode(drawingToolType)
        setPaintStyle(drawingToolType)
    }

    private fun setCurrentState(drawingToolType: DrawingToolType) {
        currentState =
            when (drawingToolType) {
                DrawingToolType.PEN -> PenState(strokes)
                DrawingToolType.RECTANGULAR -> RectangularState(strokes)
                DrawingToolType.CIRCLE -> CircleState(strokes)
                DrawingToolType.ERASER -> {
                    EraserState(strokes)
                }

                DrawingToolType.RESET -> {
                    strokes.clear()
                    cacheBitmap = null
                    cacheCanvas = null
                    invalidate()
                    currentState
                }
            }
    }

    private fun setEraseMode(drawingToolType: DrawingToolType) {
        if (drawingToolType == DrawingToolType.ERASER) {
            currentPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            currentPaint.xfermode = null
        }
    }

    private fun setPaintStyle(drawingToolType: DrawingToolType) {
        currentPaint.style =
            when (drawingToolType) {
                DrawingToolType.PEN, DrawingToolType.ERASER, DrawingToolType.RESET -> Paint.Style.STROKE
                DrawingToolType.RECTANGULAR, DrawingToolType.CIRCLE -> Paint.Style.FILL
            }
    }

    private fun updateCache() {
        if (cacheBitmap == null) {
            cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            cacheCanvas = Canvas(cacheBitmap ?: return)
        }
        val lastStroke = strokes.value.lastOrNull() ?: return
        cacheCanvas?.drawPath(lastStroke.path, lastStroke.paint)
    }

    companion object {
        private const val DEFAULT_LINE_COLOR = Color.RED
        private const val DEFAULT_LINE_WIDTH = 50f
    }
}
