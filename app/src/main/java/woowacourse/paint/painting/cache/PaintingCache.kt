package woowacourse.paint.painting.cache

import java.util.Stack

class PaintingCache {

    private val undoHistory: Stack<PaintingHistory> = Stack()
    private val redoHistory: Stack<PaintingHistory> = Stack()

    fun add(paintingHistory: PaintingHistory) {
        undoHistory.add(paintingHistory)
        redoHistory.clear()
    }

    fun undo(
        onSuccess: (history: PaintingHistory) -> Unit,
        onFailure: () -> Unit
    ) {
        runCatching {
            undoHistory.pop()?.let {
                onSuccess(it.invert())
                redoHistory.add(it.invert())
            }
        }.onFailure {
            onFailure()
        }
    }

    fun redo(
        onSuccess: (history: PaintingHistory) -> Unit,
        onFailure: () -> Unit
    ) {
        runCatching {
            redoHistory.pop()?.let {
                onSuccess(it.invert())
                undoHistory.add(it.invert())
            }
        }.onFailure {
            onFailure()
        }
    }
}
