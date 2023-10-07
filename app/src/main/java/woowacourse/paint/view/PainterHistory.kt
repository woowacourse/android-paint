package woowacourse.paint.view

import android.graphics.Canvas

class PainterHistory(
    private val undoes: ArrayList<Painter> = ArrayList(),
    private val redoes: ArrayList<Painter> = ArrayList(),
    private var currentPainter: Painter = BrushPainter(),
) {
    fun draw(canvas: Canvas) {
        undoes.forEach { painter -> painter.draw(canvas) }
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
        undoes.add(currentPainter)
        currentPainter.onActionDown(x, y)
    }

    fun onActionMove(x: Float, y: Float) {
        currentPainter.onActionMove(x, y)
    }

    fun onActionUp(x: Float, y: Float) {
        currentPainter = currentPainter.extract()
    }

    fun undo() {
        val redo = undoes.removeLastOrNull() ?: return
        redoes.add(redo)
    }

    fun redo() {
        val undo = redoes.removeLastOrNull() ?: return
        undoes.add(undo)
    }
}
