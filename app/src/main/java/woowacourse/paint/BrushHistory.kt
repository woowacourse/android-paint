package woowacourse.paint

import android.graphics.Canvas
import android.graphics.RectF

class BrushHistory(
    private var history: MutableList<Brush> = mutableListOf(),
) {

    private var last = 0

    fun drawPath(canvas: Canvas) {
        (0 until last).forEach { i ->
            history[i].drawPath(canvas)
        }
    }

    fun add(brush: Brush) {
        history = history.subList(0, last++)
        history.add(brush)
    }

    fun removeAt(x: Float, y: Float) {
        (last - 1 downTo 0).forEach { index ->
            val bounds = RectF()
            val currentBrush = history[index]
            currentBrush.path.computeBounds(bounds, true)
            if (bounds.contains(x, y)) {
                last--
                history.removeAt(index)
                return
            }
        }
    }

    fun undo() {
        if (last > 0) --last
    }

    fun redo() {
        if (history.size > last) last++
    }
}
