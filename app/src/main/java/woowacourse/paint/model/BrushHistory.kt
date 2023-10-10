package woowacourse.paint.model

import android.graphics.Canvas

class BrushHistory {
    private val history = mutableListOf<Brush>()

    private val undoHistory = mutableListOf<Brush>()

    val hasHistory: Boolean
        get() = history.isNotEmpty()

    val hasUndoHistory: Boolean
        get() = undoHistory.isNotEmpty()

    operator fun plusAssign(brush: Brush) {
        history.add(brush)
        undoHistory.clear()
    }

    fun drawOn(canvas: Canvas) {
        history.forEach { brush -> brush.drawOn(canvas) }
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
