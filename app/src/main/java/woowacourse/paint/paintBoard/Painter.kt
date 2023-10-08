package woowacourse.paint.paintBoard

import android.graphics.Canvas
import android.view.MotionEvent

class Painter {
    private var brush = Brush()
    private val painting: MutableList<Line> = mutableListOf()

    fun changeBrush(width: Float) {
        brush = brush.changeBrush(width)
    }

    fun changeBrush(color: Int) {
        brush = brush.changeBrush(color)
    }

    fun drawPainting(canvas: Canvas) {
        painting.forEach { line -> canvas.drawPath(line.path, line.brush.paint) }
    }

    fun drawDot(event: MotionEvent) {
        painting.last().path.moveTo(event.x, event.y)
        painting.last().path.lineTo(event.x, event.y)
    }

    fun drawLine(event: MotionEvent) {
        painting.last().path.lineTo(event.x, event.y)
    }

    fun savePainting() {
        painting.add(Line(brush = brush))
    }
}
