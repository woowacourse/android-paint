package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import android.graphics.Paint

class Painting {
    private val paintedLines: MutableList<Line> = mutableListOf()
    private lateinit var line: Line

    fun drawLines(canvas: Canvas) {
        paintedLines.forEach { line -> line.draw(canvas) }
    }

    fun changeColor(color: Int) {
        val paint = Paint().apply { this.color = color }
        changePaint(paint)
    }

    fun changeWidth(width: Float) {
        val paint = Paint().apply { this.strokeWidth = width }
        changePaint(paint)
    }

    private fun changePaint(paint: Paint) {
        line = Line(paint = paint)
        paintedLines.add(line)
    }

    fun drawLine(pointX: Float, pointY: Float) {
        line.lineTo(pointX, pointY)
    }

    fun movePoint(pointX: Float, pointY: Float) {
        line.moveTo(pointX, pointY)
    }
}
