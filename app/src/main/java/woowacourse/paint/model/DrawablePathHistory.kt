package woowacourse.paint.model

data class DrawablePathHistory(
    private val _paths: MutableList<DrawablePath> = mutableListOf(),
) {
    val paths: List<DrawablePath>
        get() = _paths.toList()

    fun add(path: DrawablePath) {
        _paths.add(path)
    }
}
