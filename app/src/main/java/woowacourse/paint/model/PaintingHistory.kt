package woowacourse.paint.model

import java.util.Stack

class PaintingHistory {
    private val undoHistory = mutableListOf<Painting>()
    private val redoHistory: Stack<Painting> = Stack()

    fun addHistory(track: Painting) {
        undoHistory.add(track)
    }

    fun forEach(action: (Painting) -> Unit) {
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
