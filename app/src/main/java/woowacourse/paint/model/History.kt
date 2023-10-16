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
        runCatching {
            _paintings.removeLast()
        }.onSuccess { painting ->
            cache.add(painting)
        }
    }

    fun redo() {
        runCatching {
            cache.removeLast()
        }.onSuccess { painting ->
            _paintings.add(painting)
        }
    }

    fun restoreHistory(latestHistory: History) {
        _paintings.addAll(latestHistory.paintings)
        cache.addAll(latestHistory.cache)
    }
}
