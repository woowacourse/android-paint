package woowacourse.paint.model.rectangle

import android.graphics.Canvas
import android.graphics.RectF
import woowacourse.paint.model.Sketch

data class Rectangle(
    private val vertex: RectangleVertex,
    private val color: Int,
    private val strokeWidth: Float,
) : Sketch(color, strokeWidth) {
    private val startX = vertex.startX
    private val startY = vertex.startY
    private val endX = vertex.endX
    private val endY = vertex.endY

    private val rectF =
        RectF(
            startX.coerceAtMost(endX),
            startY.coerceAtMost(endY),
            startX.coerceAtLeast(endX),
            startY.coerceAtLeast(endY),
        )

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    fun isTouched(
        x: Float,
        y: Float,
    ): Boolean {
        return x >= vertex.startX && x <= vertex.endX && y >= vertex.startY && y <= vertex.endY
    }
}
