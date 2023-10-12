package woowacourse.paint.model.drawing

class DrawingHistory(drawings: List<Drawing> = listOf()) {
    private val _drawings: MutableList<Drawing> = drawings.toMutableList()
    val drawings: List<Drawing> get() = _drawings

    fun addDrawing(drawing: Drawing) {
        _drawings.add(drawing)
    }

    fun remove(drawing: Drawing) {
        _drawings.remove(drawing)
    }

    fun removeAll() {
        _drawings.clear()
    }
}
