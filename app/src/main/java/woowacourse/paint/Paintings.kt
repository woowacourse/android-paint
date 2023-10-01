package woowacourse.paint

class Paintings(item: MutableList<Painting> = mutableListOf()) {
    private val _paintings = item
    val painting: List<Painting> = _paintings

    fun storePainting(painting: Painting) {
        _paintings.add(painting)
    }
}
