package woowacourse.paint.brush.draw

import android.graphics.Paint
import kotlin.math.pow
import kotlin.math.sqrt

data class Circle(
    val currentX: Float = DEFAULT_POINT,
    val currentY: Float = DEFAULT_POINT,
    val radius: Float = DEFAULT_RADIUS,
    val paint: Paint = Paint(),
): Draw {
    var currentRadius: Float = radius

    override fun drawing(x: Float, y: Float) {
        currentRadius = sqrt((currentX - x).pow(2) + (currentY - y).pow(2))
    }

    companion object {
        private const val DEFAULT_POINT = 0f
        private const val DEFAULT_RADIUS = 0f
    }
}
