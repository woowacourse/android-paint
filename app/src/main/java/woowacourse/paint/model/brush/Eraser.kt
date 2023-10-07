package woowacourse.paint.model.brush

import android.graphics.RectF

object Eraser : Brush() {
    override fun setStyle() = Unit

    fun erase(cursorX: Float, cursorY: Float) {
        previousDraw.lastOrNull {
            val bounds = RectF()
            it.first.computeBounds(bounds, false)
            bounds.contains(cursorX, cursorY)
        }?.let {
            previousDraw.remove(it)
        }
    }
}
