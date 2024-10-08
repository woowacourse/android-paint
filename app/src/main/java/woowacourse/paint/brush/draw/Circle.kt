package woowacourse.paint.brush.draw

import android.graphics.Paint
import kotlin.math.pow
import kotlin.math.sqrt

data class Circle(
    val currentX: Float = DEFAULT_POINT,
    val currentY: Float = DEFAULT_POINT,
    val radius: Float = DEFAULT_RADIUS,
    override val paint: Paint = Paint(),
) : Draw {
    var currentRadius: Float = radius

    override fun drawing(
        x: Float,
        y: Float,
    ) {
        currentRadius = calculateRadius(currentX, currentY, x, y)
    }

    private fun calculateRadius(
        centerX: Float,
        centerY: Float,
        pointX: Float,
        pointY: Float,
    ): Float {
        return sqrt((centerX - pointX).pow(MID_POINT) + (centerY - pointY).pow(MID_POINT))
    }

    companion object {
        private const val DEFAULT_POINT = 0f
        private const val DEFAULT_RADIUS = 0f
        private const val MID_POINT = 2
    }
}
