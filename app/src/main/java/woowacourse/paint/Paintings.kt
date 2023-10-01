package woowacourse.paint

class Paintings(private val paintings: MutableList<Painting>) {
    fun storePainting(painting: Painting) {
        paintings.add(painting)
    }
}
