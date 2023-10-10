package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import woowacourse.paint.presentation.ui.canvas.shape.PaintTool

class PaintTools(value: List<PaintTool> = listOf()) {
    private val _value = value.toMutableList()

    fun add(shape: PaintTool) {
        _value.add(shape)
    }

    fun draw(canvas: Canvas) {
        _value.forEach { shape -> shape.draw(canvas) }
    }

    fun clear() {
        _value.clear()
    }
}
