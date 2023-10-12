package woowacourse.paint.presentation.ui.canvas.painttool

import android.graphics.Canvas

class PaintTools(value: List<PaintTool> = listOf()) {
    private val _value = value.toMutableList()
    private val history = mutableListOf<PaintTool>()

    fun add(paintTool: PaintTool) {
        history.clear()
        _value.add(paintTool)
    }

    fun draw(canvas: Canvas) {
        _value.forEach { paintTool -> paintTool.draw(canvas) }
    }

    fun undo() = _value.removeLastOrNull()?.let { history.add(it) }

    fun redo() = history.removeLastOrNull()?.let { _value.add(it) }

    fun clear() {
        _value.clear()
    }
}
