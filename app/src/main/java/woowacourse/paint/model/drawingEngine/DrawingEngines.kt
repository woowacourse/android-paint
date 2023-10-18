package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.view.View
import woowacourse.paint.model.drawingEngine.error.EmptyDrawingEnginesError
import woowacourse.paint.model.drawingEngine.shape.RectangleEraserDrawingEngine
import java.util.Stack

class DrawingEngines(value: List<DrawingEngine> = mutableListOf()) {

    private val _value: MutableList<DrawingEngine> = value.toMutableList()

    private val undoStack: Stack<DrawingEngine> = Stack()

    fun last(): DrawingEngine {
        if (_value.isEmpty()) throw EmptyDrawingEnginesError()
        return _value.last()
    }

    fun draw(canvas: Canvas) {
        _value.forEach {
            it.draw(canvas)
        }
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
