package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.R
import woowacourse.paint.model.BrushState
import woowacourse.paint.model.DrawingToolType
import woowacourse.paint.model.CircleState
import woowacourse.paint.model.EraserState
import woowacourse.paint.model.PenState
import woowacourse.paint.model.RectangularState
import woowacourse.paint.model.Stroke

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val strokes = mutableListOf<Stroke>()
    private var currentState: BrushState = PenState()
    private var currentPath: Path? = null
    private val currentPaint: Paint = Paint()

    init {
        initializePaint(context, attrs)
    }

    private fun initializePaint(context: Context, attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0,
            0
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
        strokes.forEach { stroke ->
            canvas.drawPath(stroke.path, stroke.paint)
        }
        currentState.onDraw(canvas, currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = currentState.onTouchDown(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath?.let { currentState.onTouchMove(it, pointX, pointY) }
            }

            MotionEvent.ACTION_UP -> {
                currentPath?.let { currentState.onTouchUp(this, it, currentPaint) }
                currentPath = null
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setDrawingToolType(drawingToolType: DrawingToolType) {
        currentState = when (drawingToolType) {
            DrawingToolType.PEN -> PenState()
            DrawingToolType.RECTANGULAR -> RectangularState()
            DrawingToolType.CIRCLE -> CircleState()
            DrawingToolType.ERASER -> EraserState()
            DrawingToolType.RESET -> {
                strokes.clear()
                invalidate()
                currentState
            }
        }

        currentPaint.style = when (drawingToolType) {
            DrawingToolType.PEN, DrawingToolType.ERASER, DrawingToolType.RESET -> Paint.Style.STROKE
            DrawingToolType.RECTANGULAR, DrawingToolType.CIRCLE -> Paint.Style.FILL
        }
    }

    fun addStroke(stroke: Stroke) {
        strokes.add(stroke)
        invalidate()
    }

    fun eraseIntersectingStrokes(eraserPath: Path) {
        val eraserBounds = RectF()
        eraserPath.computeBounds(eraserBounds, true)
        val strokesToRemove = mutableListOf<Int>()
        strokes.forEachIndexed { index, stroke ->
            val strokeBounds = RectF()
            stroke.path.computeBounds(strokeBounds, true)
            if (RectF.intersects(eraserBounds, strokeBounds) && isPathIntersecting(
                    stroke.path,
                    eraserPath
                )
            ) {
                strokesToRemove.add(index)
            }
        }

        strokesToRemove.asReversed().forEach { index ->
            strokes.removeAt(index)
        }
        invalidate()
    }

    private fun isPathIntersecting(path1: Path, path2: Path): Boolean {
        val intersectionPath = Path()
        intersectionPath.op(path1, path2, Path.Op.INTERSECT)
        return !intersectionPath.isEmpty
    }

    fun setLineColor(color: Int) {
        currentPaint.color = color
    }

    fun setLineWidth(width: Float) {
        currentPaint.strokeWidth = width
    }

    companion object {
        private const val DEFAULT_LINE_COLOR = Color.RED
        private const val DEFAULT_LINE_WIDTH = 50f
    }
}
