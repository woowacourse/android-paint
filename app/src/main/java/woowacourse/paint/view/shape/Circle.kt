package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint

data class Circle(
    override val startX: Float,
    override val startY: Float,
    override val paint: Paint,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private var radius = 0f

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        radius = Math.sqrt(
            ((x - startX) * (x - startX) + (y - startY) * (y - startY)).toDouble(),
        ).toFloat() + strokeWidth / 2
    }

    override fun draw(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle(startX, startY, radius, paint)
    }
}
