package woowacourse.paint.model.brush

import android.graphics.RectF

object Eraser : Brush() {

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
