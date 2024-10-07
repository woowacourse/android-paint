package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.abs
import kotlin.math.min

class CircleShape(
    private var centerX: Float,
    private var centerY: Float,
    private var radius: Float = 0f,
) : Shape {
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
