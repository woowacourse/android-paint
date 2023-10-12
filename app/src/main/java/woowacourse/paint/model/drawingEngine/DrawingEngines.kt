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
        if (_value.isEmpty()) throwNoShapeError()
        return _value.last()
    }

    fun add(drawingEngine: DrawingEngine) {
        _value.add(drawingEngine)
        undoStack.clear()
    }

    /*
    선 형태의 도형을 사용할 때는 해당 함수를 이용해 객체를 추가해야 합니다. 부드러운 곡선 기능을 제공합니다.
     */
    fun add(drawingEngine: DrawingEngine, pointX: Float, pointY: Float) {
        _value.add(drawingEngine)
        updateLastPoint(pointX, pointY)
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
        val rectangleEraserDrawingEngine = RectangleEraserDrawingEngine().apply {
            changePosition(0f, 0f, view.width.toFloat(), view.height.toFloat())
        }
        _value.add(rectangleEraserDrawingEngine)
        undoStack.clear()
    }

    private fun throwNoShapeError(): Nothing = throw IllegalArgumentException("도형이 존재하지 않습니다.")

    companion object {
        var lastX: Float = 0f
            private set
        var lastY: Float = 0f
            private set

        fun updateLastPoint(x: Float, y: Float) {
            lastX = x
            lastY = y
        }
    }
}
