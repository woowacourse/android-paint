package woowacourse.paint

import android.graphics.Canvas
import android.graphics.RectF

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

    fun removeAt(x: Float, y: Float) {
        history.removeIf {
            val bounds = RectF()
            it.path.computeBounds(bounds, true)
            bounds.contains(x, y)
        }
    }
}
