package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint

data class Rectangle(
    override val startX: Float,
    override val startY: Float,
    override val paint: Paint,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private var endX: Float = startX
    private var endY: Float = startY

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        endX = x
        endY = y
    }

    override fun draw(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawRect(startX, startY, endX, endY, paint)
    }
}
