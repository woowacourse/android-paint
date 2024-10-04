package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(
    val rectangleVertex: RectangleVertex,
    val color: Int,
    val strokeWidth: Float,
) : Sketch() {
    private val startX = rectangleVertex.startX
    private val startY = rectangleVertex.startY
    private val endX = rectangleVertex.endX
    private val endY = rectangleVertex.endY

    private val paint =
        Paint().apply {
            color = this@Rectangle.color
            style = Paint.Style.STROKE
            strokeWidth = this@Rectangle.strokeWidth
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

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
}
