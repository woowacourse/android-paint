package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.abs
import kotlin.math.sqrt

data class Circle(
    val centerX: Float,
    val centerY: Float,
    var radius: Float,
    val paint: Paint,
) : Drawable {

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    fun changeRadius(x: Float, y: Float) {
        radius = sqrt(abs(centerX - x) * abs(centerX - x) + abs(centerY - y) + abs(centerY - y))
    }
}
