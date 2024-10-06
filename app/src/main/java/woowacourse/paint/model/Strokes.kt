package woowacourse.paint.model

class Strokes(value: MutableList<Stroke> = mutableListOf()) {
    private val _value: MutableList<Stroke> = value
    val value: List<Stroke>
        get() = _value

    fun add(stroke: Stroke) {
        _value.add(stroke)
    }

    fun removeAt(index: Int) {
        _value.removeAt(index)
    }

    fun clear() {
        _value.clear()
    }
}
