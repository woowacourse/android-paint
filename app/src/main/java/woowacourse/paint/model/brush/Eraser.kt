package woowacourse.paint.model.brush

import android.graphics.RectF

object Eraser : Brush() {
    override fun setStyle() = Unit

    fun erase(cursorX: Float, cursorY: Float) {
        previousDrawings.lastOrNull {
            val bounds = RectF()
            it.first.computeBounds(bounds, false)
            bounds.contains(cursorX, cursorY)
        }?.let {
            previousDrawings.remove(it)
        }
    }
}
