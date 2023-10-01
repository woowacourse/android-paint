package woowacourse.paint.model.line

class Lines(value: List<Line> = mutableListOf()) {

    private var _value: MutableList<Line>
    val value: List<Line> get() = _value.toList()

    init {
        this._value = value.toMutableList()
    }

    fun last(): Line {
        if (_value.isEmpty()) throwNoLineError()
        return _value.last()
    }

    fun add(line: Line) {
        _value.add(line)
    }

    private fun throwNoLineError(): Nothing = throw IllegalArgumentException("선이 존재하지 않습니다.")
}
