package woowacourse.paint.canvas.drawing

import android.graphics.Canvas
import android.view.MotionEvent

class Drawings(
    private val drawings: MutableList<Drawing> = mutableListOf(),
    private val drawingsCanceled: MutableList<Drawing> = mutableListOf(),
) {

    fun onDrawingTouchEvent(event: MotionEvent): Boolean {
        return drawings.last().onTouchEvent(event)
    }

    fun drawAll(canvas: Canvas) {
        drawings.forEach { drawing ->
            drawing.onDraw(canvas)
        }
    }

    fun add(drawing: Drawing) {
        drawingsCanceled.clear()
        drawings.add(drawing)
    }

    fun checkLastDrawingEmpty() {
        drawings.lastOrNull()?.let { drawing ->
            if (drawing.path.isEmpty) drawings.remove(drawing)
        }
    }

    fun undo() {
        drawings.removeLastOrNull()?.let { drawing ->
            drawingsCanceled.add(drawing)
        }
    }

    fun redo() {
        drawingsCanceled.removeLastOrNull()?.let { drawing ->
            drawings.add(drawing)
        }
    }

    fun clear() {
        drawings.clear()
        drawingsCanceled.clear()
    }
}
