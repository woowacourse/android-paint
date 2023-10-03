package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.pow
import kotlin.math.sqrt

class Brush(private val path: Path, private val paint: Paint) {
    private var lastPosition = Pair(0F, 0F)

    fun start(x: Float, y: Float) {
        path.moveTo(x + ADJUSTMENT, y + ADJUSTMENT)
        path.lineTo(x, y)
        lastPosition = Pair(x, y)
    }

    fun move(x: Float, y: Float): Boolean {
        if (available(x, y)) {
            path.lineTo(x, y)
            lastPosition = Pair(x, y)
            return true
        }
        return false
    }

    private fun available(x: Float, y: Float) = calculateDistance(x, y) > THRESHOLD

    private fun calculateDistance(x: Float, y: Float): Double {
        val x = (lastPosition.first - x).toDouble().pow(2.0)
        val y = (lastPosition.second - y).toDouble().pow(2.0)
        return sqrt(x + y)
    }

    fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    companion object {
        private const val ADJUSTMENT = Float.MIN_VALUE
        private const val THRESHOLD = 5
    }
}
