package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas

class Painting {
    private val drawnLines: Lines = Lines()
    private var drawingLine: Line = Line()

    fun drawLines(canvas: Canvas) {
        drawnLines.draw(canvas)
    }

    fun changeColor(color: Int) {
        val palette = drawingLine.palette.changeColor(color)
        changePalette(palette)
    }

    fun changeWidth(width: Float) {
        val palette = drawingLine.palette.changeWidth(width)
        changePalette(palette)
    }

    private fun changePalette(palette: Palette) {
        drawingLine = Line(palette = palette)
        drawnLines.add(drawingLine)
    }

    fun drawLine(pointX: Float, pointY: Float) {
        drawingLine.lineTo(pointX, pointY)
    }

    fun movePoint(pointX: Float, pointY: Float) {
        drawingLine.moveTo(pointX, pointY)
    }
}
