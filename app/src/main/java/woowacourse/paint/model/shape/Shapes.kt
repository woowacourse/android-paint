package woowacourse.paint.model.shape

class Shapes(value: List<Shape> = mutableListOf()) {

    private var _value: MutableList<Shape>
    val value: List<Shape> get() = _value.toList()

    init {
        this._value = value.toMutableList()
    }

    fun last(): Shape {
        if (_value.isEmpty()) throwNoShapeError()
        return _value.last()
    }

    fun add(shape: Shape) {
        _value.add(shape)
    }

    private fun throwNoShapeError(): Nothing = throw IllegalArgumentException("도형이 존재하지 않습니다.")
}
