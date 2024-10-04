package woowacourse.paint.util

import kotlin.math.sqrt

fun calculateDistance(
    x1: Float,
    y1: Float,
    x2: Float,
    y2: Float,
): Float {
    return sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
}
