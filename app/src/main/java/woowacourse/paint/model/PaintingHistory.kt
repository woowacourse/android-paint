package woowacourse.paint.model

import java.util.Stack

class PaintingHistory {
    private val history = mutableListOf<PaintingElement>()
    private val redoHistory: Stack<PaintingElement> = Stack()

    fun addHistory(track: PaintingElement) {
        history.add(track)
    }

    fun forEach(action: (PaintingElement) -> Unit) {
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
