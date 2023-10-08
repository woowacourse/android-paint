package woowacourse.paint.model

import java.util.Stack

class PaintingHistory {
    private val undoHistory = mutableListOf<PaintingElement>()
    private val redoHistory: Stack<PaintingElement> = Stack()

    fun addHistory(track: PaintingElement) {
        undoHistory.add(track)
    }

    fun forEach(action: (PaintingElement) -> Unit) {
        undoHistory.forEach(action)
    }

    fun clear() {
        undoHistory.clear()
        redoHistory.clear()
    }

    fun undo() {
        if (undoHistory.isNotEmpty()) {
            redoHistory.add(undoHistory.removeLast())
        }
    }

    fun redo() {
        if (redoHistory.isNotEmpty()) {
            undoHistory.add(redoHistory.pop())
        }
    }
}
