package woowacourse.paint.model

import android.graphics.Canvas

class Brushes() {
    private val brushes = mutableListOf<Brush>()

    private val undoHistory = mutableListOf<Brush>()

    operator fun plusAssign(brush: Brush) {
        brushes.add(brush)
        undoHistory.clear()
    }

    fun drawOn(canvas: Canvas) {
        brushes.forEach { brush -> brush.drawOn(canvas) }
    }

    fun last(): Brush {
        return brushes.last()
    }

    fun undo() {
        if (brushes.isNotEmpty()) {
            undoHistory.add(brushes.removeLast())
        }
    }

    fun redo() {
        if (undoHistory.isNotEmpty()) {
            brushes.add(undoHistory.removeLast())
        }
    }

    fun clear() {
        brushes.clear()
    }
}
