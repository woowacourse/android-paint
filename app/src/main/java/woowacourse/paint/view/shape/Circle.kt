package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper

@Parcelize
data class Circle(
    override val startX: Float,
    override val startY: Float,
    override val paint: PaintWrapper,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private var radius = 0f
    private val circlePaint = paint.toPaint()

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        radius = Math.sqrt(
            ((x - startX) * (x - startX) + (y - startY) * (y - startY)).toDouble(),
        ).toFloat() + strokeWidth / 2
    }

    override fun draw(canvas: Canvas) {
        circlePaint.style = Paint.Style.FILL
        canvas.drawCircle(startX, startY, radius, circlePaint)
    }
}
