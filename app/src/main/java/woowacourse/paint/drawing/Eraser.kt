package woowacourse.paint.drawing

import android.graphics.RectF
import woowacourse.paint.ui.PathPoint

class Eraser {
    fun erase(drawingHistory: DrawingHistory, point: PathPoint) {
        val erasedIndex: List<Int> =
            drawingHistory.drawings.mapIndexedNotNull { index, drawing ->
                val rect = RectF()
                drawing.path.computeBounds(rect, true)
                if ((point.x in rect.left..rect.right) && (point.y in rect.top..rect.bottom)) {
                    index
                } else null
            }
        erasedIndex.forEach {
            drawingHistory.removeAt(it)
        }
    }
}
