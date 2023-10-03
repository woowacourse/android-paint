package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Brush(private val path: Path, private val paint: Paint) {
    private var lastPoint = Point(0F, 0F)

    fun start(x: Float, y: Float) = start(Point(x, y))

    private fun start(point: Point) {
        path.moveTo(point.x + ADJUSTMENT, point.y + ADJUSTMENT)
        path.lineTo(point.x, point.y)
        lastPoint = point
    }

    fun move(x: Float, y: Float) = move(Point(x, y))

    private fun move(point: Point): Boolean {
        if (isDrawable(point)) {
            path.lineTo(point.x, point.y)
            lastPoint = point
            return true
        }
        return false
    }

    private fun isDrawable(point: Point) = point.distanceTo(lastPoint) > THRESHOLD

    fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    companion object {
        private const val ADJUSTMENT = Float.MIN_VALUE
        private const val THRESHOLD = 5
    }
}
