package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper
import kotlin.math.pow
import kotlin.math.sqrt

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
        radius = distance(x, y) + strokeWidth / 2
    }

    private fun distance(x: Float, y: Float): Float {
        // x 변위
        val dx = x - startX
        val dy = y - startY
        return sqrt(
            (dx.pow(2) + dy.pow(2)).toDouble(),
        ).toFloat()
    }

    override fun draw(canvas: Canvas) {
        circlePaint.style = Paint.Style.FILL
        canvas.drawCircle(startX, startY, radius, circlePaint)
    }
}
