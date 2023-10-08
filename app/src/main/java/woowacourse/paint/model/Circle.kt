package woowacourse.paint.model

import kotlin.math.abs
import kotlin.math.min

class Circle(currentX: Float, currentY: Float, x: Float, y: Float) : Shape {
    val centerX: Float = min(currentX, x) + abs(x - currentX).div(2)
    val centerY: Float = min(currentY, y) + abs(y - currentY).div(2)
    val radius: Float = abs(centerX - currentX)
}
