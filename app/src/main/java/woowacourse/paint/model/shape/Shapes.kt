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

    /*
    선 형태의 도형을 사용할 때는 해당 함수를 이용해 객체를 추가해야 합니다. 부드러운 곡선 기능을 제공합니다.
     */
    fun add(shape: Shape, pointX: Float, pointY: Float) {
        _value.add(shape)
        updateLastPoint(pointX, pointY)
    }

    private fun throwNoShapeError(): Nothing = throw IllegalArgumentException("도형이 존재하지 않습니다.")

    companion object {
        var lastX: Float = 0f
            private set
        var lastY: Float = 0f
            private set

        fun updateLastPoint(x: Float, y: Float) {
            lastX = x
            lastY = y
        }
    }
}
