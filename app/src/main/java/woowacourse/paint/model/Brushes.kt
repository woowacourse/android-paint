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

    fun undo(onSuccess: () -> Unit = {}) {
        if (brushes.isNotEmpty()) {
            undoHistory.add(brushes.removeLast())
            onSuccess()
        }
    }

    fun redo(onSuccess: () -> Unit = {}) {
        if (undoHistory.isNotEmpty()) {
            brushes.add(undoHistory.removeLast())
            onSuccess()
        }
    }

    fun clear(callback: () -> Unit = {}) {
        brushes.clear()
        undoHistory.clear()
        callback()
    }
}
