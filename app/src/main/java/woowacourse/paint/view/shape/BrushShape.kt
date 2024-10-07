package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint

sealed class BrushShape(
    open val startX: Float,
    open val startY: Float,
    open val paint: Paint,
    open val strokeWidth: Float,
) {
    abstract fun updatePosition(
        x: Float,
        y: Float,
    )

    abstract fun draw(canvas: Canvas)
}
