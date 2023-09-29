package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas

class Painting {
    private val paintedLines: MutableList<Line> = mutableListOf()
    private var line: Line = Line()

    fun drawLines(canvas: Canvas) {
        paintedLines.forEach { line -> line.draw(canvas) }
    }

    fun changeColor(color: Int) {
        val palette = line.palette.changeColor(color)
        changePalette(palette)
    }

    fun changeWidth(width: Float) {
        val palette = line.palette.changeWidth(width)
        changePalette(palette)
    }

    private fun changePalette(palette: Palette) {
        line = Line(palette = palette)
        paintedLines.add(line)
    }

    fun drawLine(pointX: Float, pointY: Float) {
        line.lineTo(pointX, pointY)
    }

    fun movePoint(pointX: Float, pointY: Float) {
        line.moveTo(pointX, pointY)
    }
}
