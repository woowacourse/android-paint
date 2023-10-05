package woowacourse.paint.util

import android.graphics.RectF
import woowacourse.paint.model.Point

fun createRectFOf(point: Point, otherPoint: Point): RectF {
    val minX = minOf(point.x, otherPoint.x)
    val minY = minOf(point.y, otherPoint.y)
    val maxX = maxOf(point.x, otherPoint.x)
    val maxY = maxOf(point.y, otherPoint.y)
    return RectF(minX, minY, maxX, maxY)
}
