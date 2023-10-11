package woowacourse.paint

import android.graphics.Canvas
import woowacourse.paint.brush.Brush
import java.util.Stack

class BrushHistory {

    private val undoes = Stack<Brush>()
    private val redoes = Stack<Brush>()

    fun drawPath(canvas: Canvas) {
        undoes.forEach { brush ->
            brush.drawPath(canvas)
        }
    }

    fun add(brush: Brush) {
        redoes.clear()
        undoes.add(brush)
    }

    fun removeAt(x: Float, y: Float) {
        (undoes.size - 1 downTo 0).forEach { index ->
            val currentBrush = undoes[index]
            val bounds = currentBrush.getBounds()
            if (bounds.contains(x, y)) {
                undoes.removeAt(index)
                return
            }
        }
    }

    fun undo() {
        if (undoes.isNotEmpty()) {
            redoes.add(undoes.pop())
        }
    }

    fun redo() {
        if (redoes.isNotEmpty()) {
            undoes.add(redoes.pop())
        }
    }

    fun clear() {
        undoes.clear()
        redoes.clear()
    }
}
