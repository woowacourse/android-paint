package woowacourse.paint.model.circle

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.model.Sketch

data class Circle(
    private val center: Center,
    private val radius: Float,
    private val color: Int,
    private val strokeWidth: Float,
) : Sketch() {
    private val paint get() =
        Paint().apply {
            color = this@Circle.color
            style = Paint.Style.STROKE
            strokeWidth = this@Circle.strokeWidth
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint)
    }
}
