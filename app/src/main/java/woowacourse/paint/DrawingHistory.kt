package woowacourse.paint

class DrawingHistory {
    private val _history = mutableListOf<DrawingElement>()
    val history: List<DrawingElement>
        get() = _history

    fun addHistory(track: DrawingElement) {
        _history.add(track)
    }
}
