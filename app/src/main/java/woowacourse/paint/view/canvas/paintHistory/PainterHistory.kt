package woowacourse.paint.view.canvas.paintHistory

import android.graphics.Canvas
import woowacourse.paint.view.canvas.painter.BrushPainter
import woowacourse.paint.view.canvas.painter.Painter
import woowacourse.paint.view.palette.PaletteMode
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape

class PainterHistory(
    private val undoes: ArrayList<Painter> = ArrayList(),
    private val redoes: ArrayList<Painter> = ArrayList(),
) {
    private var painterState = PainterState()
    private var currentPainter: Painter = BrushPainter(
        paletteColor = painterState.paletteColor,
        thickness = painterState.thickness,
    )

    fun draw(canvas: Canvas) {
        undoes.forEach { painter -> painter.draw(canvas) }
    }

    fun setPaletteColor(paletteColor: PaletteColor) {
        painterState = painterState.copy(paletteColor = paletteColor)
        currentPainter = currentPainter.setPaletteColor(paletteColor)
    }

    fun setPaintThickness(thickness: Float) {
        painterState = painterState.copy(thickness = thickness)
        currentPainter = currentPainter.setThickness(thickness)
    }

    fun setPaletteShape(paletteShape: PaletteShape) {
        painterState = painterState.copy(paletteShape = paletteShape)
        currentPainter = currentPainter.changePainter(PaletteMode.SHAPE, painterState)
    }

    fun changePaletteMode(paletteMode: PaletteMode) {
        currentPainter = currentPainter.changePainter(paletteMode, painterState)
    }

    fun onActionDown(x: Float, y: Float) {
        undoes.add(currentPainter)
        currentPainter.onActionDown(x, y)
    }

    fun onActionMove(x: Float, y: Float) {
        currentPainter.onActionMove(x, y)
    }

    fun onActionUp() {
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

    fun clear() {
        undoes.clear()
        redoes.clear()
    }
}
