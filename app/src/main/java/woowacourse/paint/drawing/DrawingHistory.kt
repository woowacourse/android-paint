package woowacourse.paint.drawing

import android.graphics.Paint
import android.graphics.Path

class DrawingHistory(drawings: MutableList<Drawing> = mutableListOf()) {
    private val _drawings = drawings.deepCopy().toMutableList()
    val drawings: List<Drawing> get() = _drawings.deepCopy()

    fun addDrawing(drawing: Drawing) {
        _drawings.add(drawing)
    }

    fun removeAt(index: Int) {
        _drawings.removeAt(index)
    }

    fun removeAll() {
        _drawings.clear()
    }

    private fun List<Drawing>.deepCopy() =
        map { it.copy(path = Path(it.path), paint = Paint(it.paint)) }
}
