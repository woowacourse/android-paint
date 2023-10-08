package woowacourse.paint.model

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Circle(currentX: Float, currentY: Float, x: Float, y: Float) : Shape {
    val centerX: Float = min(currentX, x) + abs(x - currentX).div(2)
    val centerY: Float = min(currentY, y) + abs(y - currentY).div(2)
    val radius: Float = sqrt(abs(x - centerX).pow(2) + abs(y - centerY).pow(2))
}
