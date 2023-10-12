package woowacourse.paint.model

import android.graphics.RectF
import kotlin.math.sqrt

data class Point(val x: Float, val y: Float) {
    fun distanceTo(point: Point): Float {
        val x = (this.x - point.x) * (this.x - point.x)
        val y = (this.y - point.y) * (this.y - point.y)
        return sqrt(x + y)
    }

    fun getRectF(otherPoint: Point): RectF {
        val minX = minOf(x, otherPoint.x)
        val minY = minOf(y, otherPoint.y)
        val maxX = maxOf(x, otherPoint.x)
        val maxY = maxOf(y, otherPoint.y)
        return RectF(minX, minY, maxX, maxY)
    }
}
