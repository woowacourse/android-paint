package woowacourse.paint.model

import java.util.Stack

class BrushCareTaker {
    private val mementos = Stack<BrushMemento>()
    private val undoMementos = Stack<BrushMemento>()

    var currentMemento: BrushMemento = BrushMemento(Brushes())
        private set

    val hasHistory: Boolean
        get() = mementos.isNotEmpty()

    val hasUndoHistory: Boolean
        get() = undoMementos.isNotEmpty()

    fun save(memento: BrushMemento) {
        if (memento.brushes.isEmpty() && currentMemento.brushes.isEmpty()) {
            return
        }
        mementos.push(currentMemento)
        undoMementos.clear()
        currentMemento = memento
    }

    fun undo(onSuccess: (Brushes) -> Unit) {
        val memento = mementos.pop()
        if (memento != null) {
            undoMementos.push(currentMemento)
            currentMemento = memento
            onSuccess(currentMemento.brushes)
        }
    }

    fun redo(onSuccess: (Brushes) -> Unit) {
        val memento = undoMementos.pop()
        if (memento != null) {
            mementos.push(currentMemento)
            currentMemento = memento
            onSuccess(currentMemento.brushes)
        }
    }
}
