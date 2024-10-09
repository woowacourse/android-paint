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
        val bounds = RectF()

        for (index in drawings.indices.reversed()) {
            val drawing = drawings[index]
            drawing.computeBounds(bounds)

            if (bounds.contains(x, y)) return index
        }
        return DrawingBoard.INVALID_INDEX
    }

    private fun Drawing.computeBounds(bounds: RectF) {
        path.computeBounds(bounds, true)
    }
}
