package woowacourse.paint.model

import java.util.Stack

class PaintingHistory {
    private val undoHistory = mutableListOf<DrawingTool>()
    private val redoHistory: Stack<DrawingTool> = Stack()

    fun addHistory(track: DrawingTool) {
        undoHistory.add(track)
    }

    fun forEach(action: (DrawingTool) -> Unit) {
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
