package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

object Eraser {
    fun eraseBrush(
        cursorX: Float,
        cursorY: Float,
        previousDrawings: MutableList<Pair<Path, Paint>>,
    ) {
        previousDrawings.lastOrNull {
            val bounds = RectF()
            it.first.computeBounds(bounds, false)
            bounds.contains(cursorX, cursorY)
        }?.let {
            previousDrawings.remove(it)
        }
    }
}
