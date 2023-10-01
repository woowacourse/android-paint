package woowacourse.paint

class Paintings(item: MutableList<Painting>) {
    private val _paintings = item
    val painting: List<Painting> = _paintings.toList()

    fun storePainting(painting: Painting) {
        _paintings.add(painting)
    }
}
