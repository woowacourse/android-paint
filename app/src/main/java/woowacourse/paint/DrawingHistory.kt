package woowacourse.paint

class DrawingHistory(private val _drawings: MutableList<Drawing> = mutableListOf()) {
    val drawings: List<Drawing> get() = _drawings.deepCopy()

    fun addDrawing(pathPaint: Drawing) {
        _drawings.add(pathPaint)
    }

    private fun List<Drawing>.deepCopy() = map { it.copy() }
}
