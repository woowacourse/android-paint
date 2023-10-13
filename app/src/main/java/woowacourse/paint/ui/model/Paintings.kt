package woowacourse.paint.ui.model

class Paintings(private val _paintings: MutableList<Painting> = mutableListOf()) {
    val painting: List<Painting> = _paintings

    fun storePainting(painting: Painting) {
        _paintings.add(painting)
    }

    fun removeAllPaintings() {
        _paintings.clear()
    }
}
