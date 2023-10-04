package woowacourse.paint.model

import kotlin.math.sqrt

data class Point(val x: Float, val y: Float) {
    fun distanceTo(point: Point): Float {
        val x = (this.x - point.x) * (this.x - point.x)
        val y = (this.y - point.y) * (this.y - point.y)
        return sqrt(x + y)
    }
}
