package woowacourse.paint.model

class History {
    private val cache: MutableList<Painting> = mutableListOf()
    private val _paintings: MutableList<Painting> = mutableListOf()
    val paintings: List<Painting>
        get() = _paintings.toList()

    fun add(newPainting: Painting) {
        _paintings.add(newPainting)
    }

    fun clear() {
        _paintings.clear()
    }

    fun undo() {
        try {
            cache.add(_paintings.removeLast())
        } catch (_: NoSuchElementException) {
        }
    }

    fun redo() {
        try {
            _paintings.add(cache.removeLast())
        } catch (_: NoSuchElementException) {
        }
    }
}
