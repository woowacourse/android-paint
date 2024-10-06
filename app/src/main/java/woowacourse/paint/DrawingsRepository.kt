package woowacourse.paint

import woowacourse.paint.drawing.Drawing

object DrawingsRepository {
    private val _drawings = mutableListOf<Drawing>()
    val drawings: List<Drawing>
        get() = _drawings.toList()

    fun addDrawing(drawing: Drawing) {
        _drawings.add(drawing)
    }
}
