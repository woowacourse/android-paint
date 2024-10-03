package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.tools.Circle
import woowacourse.paint.tools.DrawingTool
import woowacourse.paint.tools.Eraser
import woowacourse.paint.tools.Line
import woowacourse.paint.tools.Rectangle

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings = mutableListOf<DrawingTool>()
    private var currentDrawingTool: DrawingTool = Line()
    private val currentPaint =
        Paint().apply {
            strokeWidth = DEFAULT_STROKE_WIDTH
            color = resources.getColor(DEFAULT_STROKE_COLOR.colorRes, null)
        }

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { it.renderOnCanvas(canvas) }
        currentDrawingTool.renderOnCanvas(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentDrawingTool.setStartPoint(pointX, pointY, currentPaint)
            }

            MotionEvent.ACTION_MOVE -> {
                currentDrawingTool.draw(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                drawings.add(currentDrawingTool)
                currentDrawingTool = currentDrawingTool.initialize()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStrokeWidth(width: Float) {
        currentPaint.strokeWidth = width
    }

    fun setStrokeColor(color: Int) {
        currentPaint.color = color
    }

    fun setDrawingMode(drawingMode: DrawingMode) {
        currentDrawingTool =
            when (drawingMode) {
                DrawingMode.LINE -> Line()
                DrawingMode.RECTANGLE -> Rectangle()
                DrawingMode.CIRCLE -> Circle()
                DrawingMode.ERASE -> Eraser()
            }
    }

    fun undo() {
        drawings.removeLast()
        invalidate()
    }

    fun deleteAll() {
        drawings.clear()
        invalidate()
    }

    companion object {
        private val DEFAULT_STROKE_COLOR = PaletteColor.RED
        const val DEFAULT_STROKE_WIDTH = 20f
    }
}
