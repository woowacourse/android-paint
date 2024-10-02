package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.R
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.Stroke

class CanvasView(context: Context, attrs: AttributeSet) :
    View(context, attrs) {
    private val strokes = mutableListOf<Stroke>()
    private var currentBrushType = BrushType.PEN
    private var currentPath: Path? = null
    private val currentPaint: Paint =
        Paint().apply {
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }

    init {
        initializeCanvasView()
        initializePaint(context, attrs)
    }

    private fun initializePaint(context: Context, attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0,
            0,
        ).apply {
            initializePaintByResourceType()
        }
    }

    private fun initializeCanvasView() {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    private fun TypedArray.initializePaintByResourceType() {
        try {
            currentPaint.color = getColor(R.styleable.CustomView_lineColor, DEFAULT_LINE_COLOR)
            currentPaint.strokeWidth =
                getDimension(R.styleable.CustomView_lineWidth, DEFAULT_LINE_WIDTH)
        } finally {
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            canvas.drawPath(stroke.path, stroke.paint)
        }
        currentPath?.let { canvas.drawPath(it, currentPaint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> currentPath = Path().apply { moveTo(pointX, pointY) }
            MotionEvent.ACTION_MOVE -> currentPath?.lineTo(pointX, pointY)
            MotionEvent.ACTION_UP -> {
                currentPath?.let {
                    strokes.add(Stroke(it, Paint(currentPaint)))
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

    fun setBrushType(brushType: BrushType) {
        currentBrushType = brushType
    }

    companion object {
        private const val DEFAULT_LINE_COLOR = Color.RED
        private const val DEFAULT_LINE_WIDTH = 50f
    }
}
