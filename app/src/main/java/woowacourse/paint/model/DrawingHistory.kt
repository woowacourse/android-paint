package woowacourse.paint.model

class DrawingHistory {
    private val _history = mutableListOf<DrawingElement>()
    val history: List<DrawingElement>
        get() = _history.toList()

    fun addHistory(track: DrawingElement) {
        _history.add(track)
    }
}
