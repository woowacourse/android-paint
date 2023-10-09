package woowacourse.paint.drawing

import android.graphics.RectF
import woowacourse.paint.ui.PathPoint

class Eraser(private val drawingHistory: DrawingHistory) {
    fun erasePath(pathPoint: PathPoint) {
        val erasedIndex: List<Int> =
            drawingHistory.drawings.mapIndexedNotNull { index, drawing ->
                val rect = RectF()
                drawing.path.computeBounds(rect, true)
                if ((pathPoint.x in rect.left..rect.right) && (pathPoint.y in rect.top..rect.bottom)) {
                    index
                } else null
            }
        erasedIndex.forEach {
            drawingHistory.removeAt(it)
        }
    }
}
