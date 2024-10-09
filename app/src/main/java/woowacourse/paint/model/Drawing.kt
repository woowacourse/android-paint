package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.DrawingMode.PEN
import woowacourse.paint.model.DrawingMode.CIRCLE
import woowacourse.paint.model.DrawingMode.RECTANGLE
import woowacourse.paint.model.DrawingMode.ERASE

sealed class Drawing(
    val path: Path,
    val paint: Paint,
) {
    abstract fun setPaintStyle()

    abstract fun draw(
        startX: Float,
        startY: Float,
        currentX: Float,
        currentY: Float,
    )

    companion object {
        fun getDrawing(
            path: Path,
            paint: Paint,
            drawingMode: DrawingMode,
        ): Drawing {
            return when (drawingMode) {
                PEN -> Pen(path, paint)
                CIRCLE -> Circle(path, paint)
                RECTANGLE -> Rectangle(path, paint)
                ERASE -> Eraser(path, paint)
            }
        }
    }
}
