package woowacourse.paint.model.brush

import android.graphics.RectF

object Eraser : Brush() {
    override fun updateStyle() = Unit

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        erase(xCursor, yCursor)
        updateView()
    }

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: () -> Unit) = Unit

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: () -> Unit) = Unit

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
