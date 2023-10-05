package woowacourse.paint.model

class DrawingHistory {

    private val _history = mutableListOf<Drawing>()
    val history: List<Drawing>
        get() = _history.toList()

    fun add(drawing: Drawing) = _history.add(drawing)

    fun clearAll() = _history.clear()
}
