package woowacourse.paint.model

import java.util.Stack

class DrawingHistory {
    private val history = mutableListOf<DrawingElement>()
    private val redoHistory: Stack<DrawingElement> = Stack()

    val isHistoryEmpty
        get() = history.isEmpty()

    val isRedoHistoryEmpty
        get() = history.isEmpty()

    fun addHistory(track: DrawingElement) {
        history.add(track)
    }

    fun forEach(action: (DrawingElement) -> Unit) {
        history.forEach(action)
    }

    fun clear() {
        history.clear()
        redoHistory.clear()
    }

    fun undo() {
        if (history.isNotEmpty()) {
            redoHistory.add(history.removeLast())
        }
    }

    fun redo() {
        if (redoHistory.isNotEmpty()) {
            history.add(redoHistory.pop())
        }
    }
}
