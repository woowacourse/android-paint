package woowacourse.paint.model.rectangle

import android.graphics.Canvas
import android.graphics.RectF
import woowacourse.paint.model.Sketch

data class Rectangle(
    private val vertex: RectangleVertex,
    private val color: Int,
    private val strokeWidth: Float,
) : Sketch(color, strokeWidth) {
    private val rectF = RectF(
        vertex.startX.coerceAtMost(vertex.endX),
        vertex.startY.coerceAtMost(vertex.endY),
        vertex.startX.coerceAtLeast(vertex.endX),
        vertex.startY.coerceAtLeast(vertex.endY),
    )

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    override fun isTouched(
        x: Float,
        y: Float,
    ): Boolean {
        return x >= vertex.startX && x <= vertex.endX && y >= vertex.startY && y <= vertex.endY
    }
}
