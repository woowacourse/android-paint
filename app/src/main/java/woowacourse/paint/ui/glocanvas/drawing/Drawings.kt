package woowacourse.paint.ui.glocanvas.drawing

class Drawings(private val _items: ArrayDeque<Drawing> = ArrayDeque()) {
    val items: ArrayDeque<Drawing>
        get() = ArrayDeque<Drawing>().apply { addAll(_items) }

    fun addFirst(drawing: Drawing) {
        _items.addFirst(drawing)
    }

    fun addLast(drawing: Drawing) {
        _items.addLast(drawing)
    }

    fun removeFirst(): Drawing? {
        return if (_items.isNotEmpty()) {
            _items.removeFirst()
        } else {
            null
        }
    }

    fun removeLast(): Drawing? {
        return if (_items.isNotEmpty()) {
            _items.removeLast()
        } else {
            null
        }
    }

    fun clear() {
        _items.clear()
    }

    fun getLastDrawing(): Drawing {
        return _items.last()
    }
}
