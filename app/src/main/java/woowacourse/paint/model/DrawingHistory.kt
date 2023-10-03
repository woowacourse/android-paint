package woowacourse.paint.model

class DrawingHistory {
    private val history = mutableListOf<DrawingElement>()

    fun addHistory(track: DrawingElement) {
        history.add(track)
    }

    fun forEach(action: (DrawingElement) -> Unit) {
        history.forEach(action)
    }
}
