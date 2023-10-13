package woowacourse.paint.model.brush

import android.graphics.RectF
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint

class Eraser(private val drawingHistory: DrawingHistory) : Brush {

    private fun erasePath(point: PathPoint) {
        drawingHistory.drawings.firstOrNull { drawing ->
            val rect = RectF()
            drawing.path.computeBounds(rect, false)
            rect.contains(point.x, point.y)
        }.also { drawing -> drawingHistory.remove(drawing ?: return) }
    }

    override fun startDrawing(point: PathPoint) {
        erasePath(point)
    }

    override fun moveDrawing(point: PathPoint) {
        erasePath(point)
    }
}
