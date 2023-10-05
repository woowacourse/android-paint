package woowacourse.paint

class PathHistory(private val _paths: MutableList<PathPaint> = mutableListOf()) {
    val paths: List<PathPaint> get() = _paths.deepCopy()

    fun addPath(pathPaint: PathPaint) {
        _paths.add(pathPaint)
    }

    private fun List<PathPaint>.deepCopy() = map { it.copy() }
}
