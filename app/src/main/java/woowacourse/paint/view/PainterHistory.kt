package woowacourse.paint.view

import android.graphics.Canvas

class PainterHistory(
    private val painters: MutableList<Painter> = mutableListOf(),
    private var currentPainter: Painter = BrushPainter(),
) {
    fun draw(canvas: Canvas) {
        painters.forEach { painter ->
            painter.draw(canvas)
        }
    }

    fun setPaletteColor(paletteColor: PaletteColor) {
        currentPainter = currentPainter.setPaletteColor(paletteColor)
    }

    fun setPaintThickness(painterThickness: Float) {
        currentPainter = currentPainter.setThickness(painterThickness)
    }

    fun setPaletteShape(paletteShape: PaletteShape) {
        currentPainter = currentPainter.changePainter(PaletteMode.SHAPE, paletteShape)
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        currentPainter = currentPainter.changePainter(paletteMode)
    }

    fun onActionDown(x: Float, y: Float) {
        painters.add(currentPainter)
        currentPainter.onActionDown(x, y)
    }

    fun onActionMove(x: Float, y: Float) {
        currentPainter.onActionMove(x, y)
    }

    fun onActionUp(x: Float, y: Float) {
        currentPainter = currentPainter.extract()
    }
}
