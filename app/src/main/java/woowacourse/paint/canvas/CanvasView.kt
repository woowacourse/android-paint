package woowacourse.paint.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.Line

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private val lines: MutableList<Line> = mutableListOf()
    private var drawingLine = Line()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { it.draw(canvas) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startDrawing(event.x, event.y)
                invalidate()
                true
            }

            MotionEvent.ACTION_MOVE -> {
                quadDrawing(event.x, event.y)
                invalidate()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun startDrawing(
        x: Float,
        y: Float,
    ) {
        drawingLine.move(x, y)
        lines.add(drawingLine)
    }

    private fun quadDrawing(
        x: Float,
        y: Float,
    ) {
        drawingLine.quad(x, y)
    }

    fun changeLineWidth(brush: Brush) {
        drawingLine = Line(paint = createNewPaint(brush))
    }

    fun changeColor(brush: Brush) {
        drawingLine = Line(paint = createNewPaint(brush))
    }

    private fun createNewPaint(brush: Brush): Paint {
        return Paint().apply {
            this.color = brush.colorPalette.color
            this.strokeWidth = brush.width
        }
    }
}
