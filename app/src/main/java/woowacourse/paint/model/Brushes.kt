package woowacourse.paint.model

import android.graphics.Canvas

class Brushes() {
    private val history = mutableListOf<Brush>()

    private val undoHistory = mutableListOf<Brush>()

    val hasHistory: Boolean
        get() = history.isNotEmpty()

    val hasUndoHistory: Boolean
        get() = undoHistory.isNotEmpty()

    operator fun <T : Brush> plusAssign(brush: T) {
        history.add(brush)
        undoHistory.clear()
    }

    fun drawOn(canvas: Canvas) {
        history.forEach { brush -> brush.drawOn(canvas) }
    }

    fun last(): Brush {
        return history.last()
    }

    fun undo(onSuccess: () -> Unit = {}) {
        if (history.isNotEmpty()) {
            undoHistory.add(history.removeLast())
            onSuccess()
        }
    }

    fun redo(onSuccess: () -> Unit = {}) {
        if (undoHistory.isNotEmpty()) {
            history.add(undoHistory.removeLast())
            onSuccess()
        }
    }

    fun clear(callback: () -> Unit = {}) {
        history.clear()
        undoHistory.clear()
        callback()
    }
}
