package woowacourse.paint.paintcanvas

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.paintcanvas.shape.Shape
import java.util.Stack

object CanvasHistory {
    private var canvasHistory: MutableList<Pair<Shape, Paint>> = mutableListOf()
    private var deletedHistory = Stack<Pair<Shape, Paint>>()

    fun undo() {
        canvasHistory.removeLastOrNull()?.let {
            deletedHistory.add(it)
        }
    }

    fun redo() {
        deletedHistory.removeLastOrNull()?.let {
            canvasHistory.add(it)
        }
    }

    fun onDraw(canvas: Canvas) {
        canvasHistory.forEach {
            it.first.draw(canvas, it.second)
        }
    }

    fun addNewShape(shapePaintPair: Pair<Shape, Paint>) {
        canvasHistory.add(shapePaintPair)
        clearDeletedHistory()
    }

    fun replaceNewShape(shapePaintPair: Pair<Shape, Paint>) {
        canvasHistory.removeLastOrNull()
        canvasHistory.add(shapePaintPair)
        clearDeletedHistory()
    }

    private fun clearDeletedHistory() {
        deletedHistory.clear()
    }
}
