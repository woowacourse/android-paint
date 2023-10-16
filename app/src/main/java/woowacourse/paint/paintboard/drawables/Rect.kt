package woowacourse.paint.paintboard.drawables

import woowacourse.paint.entity.Point

class Rect(private val start: Point) {
    var end: Point = start
    val left: Float get() = Math.min(start.x, end.x)
    val right: Float get() = Math.max(start.x, end.x)
    val top: Float get() = Math.min(start.y, end.y)
    val bottom: Float get() = Math.max(start.y, end.y)

    fun update(point: Point) {
        end = point
    }
}
