package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.RectF

class Eraser(override val paintInstance: Paint = Paint()) : Brush() {
    override fun updateStyle(paint: Paint) {
        paintInstance.set(paint)
    }

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        erase(xCursor, yCursor)
        updateView()
    }

    override fun copyPaint(): Paint = Paint().apply { set(paintInstance) }

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: () -> Unit) = Unit

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: () -> Unit) = Unit
    override fun updateColor(color: Int) = Unit

    override fun updateThickness(thickness: Float) = Unit

    private fun erase(cursorX: Float, cursorY: Float) {
        previousDrawings.lastOrNull {
            val bounds = RectF()
            it.first.computeBounds(bounds, false)
            bounds.contains(cursorX, cursorY)
        }?.let {
            previousDrawings.remove(it)
        }
    }
}
