package woowacourse.paint

class History {
    private val _paintings: MutableList<Painting> = mutableListOf()
    val paintings: List<Painting>
        get() = _paintings.toList()

    fun add(newPainting: Painting) {
        _paintings.add(newPainting)
    }
}
