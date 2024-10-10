package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.abs
import kotlin.math.min

class CircleShape(
    private val centerX: Float = 0f,
    private val centerY: Float = 0f,
) : Shape {
    private var radius = 0f

    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    override fun update(
        x: Float,
        y: Float,
    ) {
        radius = min(abs(x - centerX), abs(y - centerY))
    }
}
