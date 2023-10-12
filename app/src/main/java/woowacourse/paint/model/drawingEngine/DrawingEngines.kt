package woowacourse.paint.model.drawingEngine

import android.view.View
import woowacourse.paint.model.drawingEngine.shape.RectangleEraserDrawingEngine
import java.util.Stack

class DrawingEngines(value: List<DrawingEngine> = mutableListOf()) {

    private var _value: MutableList<DrawingEngine>
    val value: List<DrawingEngine> get() = _value.toList()

    private val undoStack: Stack<DrawingEngine> = Stack()

    init {
        this._value = value.toMutableList()
    }

    fun last(): DrawingEngine {
        if (_value.isEmpty()) throw IllegalArgumentException("도형이 존재하지 않습니다.")
        return _value.last()
    }

    fun add(drawingEngine: DrawingEngine) {
        _value.add(drawingEngine)
        undoStack.clear()
    }

    fun undo() {
        if (_value.isEmpty()) return
        val removed = _value.removeAt(_value.lastIndex)
        undoStack.add(removed)
    }

    fun redo() {
        if (undoStack.isEmpty()) return
        val lastUndo = undoStack.pop()
        _value.add(lastUndo)
    }

    fun clear(view: View) {
        val rectangleEraserDrawingEngine =
            RectangleEraserDrawingEngine.createInstance(view.width.toFloat(), view.height.toFloat())
        _value.add(rectangleEraserDrawingEngine)
        undoStack.clear()
    }
}
