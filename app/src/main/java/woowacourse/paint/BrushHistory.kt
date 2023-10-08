package woowacourse.paint

import android.graphics.Canvas

class BrushHistory(
    private val history: MutableList<Brush> = mutableListOf(),
) {

    fun drawPath(canvas: Canvas) {
        history.forEach {
            it.drawPath(canvas)
        }
    }

    fun add(brush: Brush) {
        history.add(brush)
    }
}
