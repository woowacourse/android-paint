package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import woowacourse.paint.presentation.ui.canvas.shape.PaintTool

class PaintTools(value: List<PaintTool> = listOf()) {
    private val _value = value.toMutableList()
    private val history = mutableListOf<PaintTool>()

    fun add(paintTool: PaintTool) {
        history.clear()
        _value.add(paintTool)
    }

    fun draw(canvas: Canvas) {
        _value.forEach { shape -> shape.draw(canvas) }
    }

    fun undo() = _value.removeLastOrNull()?.let { history.add(it) }

    fun redo() = history.removeLastOrNull()?.let { _value.add(it) }

    fun clear() {
        _value.clear()
    }
}
