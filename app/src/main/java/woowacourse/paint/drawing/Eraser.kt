package woowacourse.paint.drawing

import android.graphics.RectF
import woowacourse.paint.ui.PathPoint

class Eraser(private val drawingHistory: DrawingHistory) {
    fun erasePath(pathPoint: PathPoint) {
        drawingHistory.drawings.firstOrNull { drawing ->
            val rect = RectF()
            drawing.path.computeBounds(rect, false)
            rect.contains(pathPoint.x, pathPoint.y)
        }.also { drawing -> drawingHistory.remove(drawing ?: return) }
    }
}
