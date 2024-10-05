package woowacourse.paint.model.circle

import android.graphics.Canvas
import woowacourse.paint.model.Sketch

data class Circle(
    private val center: Center,
    private val radius: Float,
    private val color: Int,
    private val strokeWidth: Float,
) : Sketch(color, strokeWidth) {
    override fun draw(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint)
    }
}
