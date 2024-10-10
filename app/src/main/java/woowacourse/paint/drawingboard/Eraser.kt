package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.drawingboard.Drawings.drawings

class Eraser(path: Path = Path(), paint: Paint) : Drawing(path, paint) {
    override fun updateColor(color: Int): Drawing = Eraser(path, paint)

    override fun updatePaintStyle(): Drawing = Eraser(path, paint)

    fun findRemoveItem(
        x: Float,
        y: Float,
    ): Int {
        for (index in drawings.indices.reversed()) {
            val drawing = drawings[index]
            val bounds = RectF()
            drawing.path.computeBounds(bounds, true)

            bounds.inset(PADDING, PADDING)

            val newBounds = RectF(bounds)
            if (newBounds.contains(x, y)) return index
        }
        return DrawingBoard.INVALID_INDEX
    }

    companion object {
        private const val PADDING = -20f
    }
}
