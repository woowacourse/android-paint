package woowacourse.paint

class PathHistory(private val _paths: MutableList<PathPaint> = mutableListOf()) {
    val paths: List<PathPaint> get() = _paths.toList()

    fun addPath(pathPaint: PathPaint) {
        _paths.add(pathPaint)
    }
}
