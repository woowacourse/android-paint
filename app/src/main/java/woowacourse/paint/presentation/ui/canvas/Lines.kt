package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas

class Lines(value: List<Line> = listOf()) {
    private val _value = value.toMutableList()

    fun add(line: Line) {
        _value.add(line)
    }

    fun draw(canvas: Canvas) {
        _value.forEach { line -> line.draw(canvas) }
    }
}
