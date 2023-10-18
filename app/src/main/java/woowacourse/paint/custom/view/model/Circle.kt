package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.pow
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

    override fun keepDrawing(x: Float, y: Float) {
        radius = sqrt((centerX - x).pow(2) + (centerY - y).pow(2))
    }
}
