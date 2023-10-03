package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path
import kotlin.math.abs

class Brush(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {

    private var prevX: Float = 0f
    private var prevY: Float = 0f

    init {
        setupPaint()
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun startDrawing(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        prevX = x
        prevY = y
    }

    fun keepDrawing(x: Float, y: Float) {
        if (abs(x - prevX) >= MOVE_THRESHOLD || abs(y - prevY) >= MOVE_THRESHOLD) {
            path.quadTo(prevX, prevY, (x + prevX) / 2, (y + prevY) / 2)
            prevX = x
            prevY = y
        }
    }

    fun lineToPrevPoint() {
        path.lineTo(prevX, prevY)
    }

    private fun setupPaint() {
        setStrokeWidth(10f)
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }

    companion object {
        private const val MOVE_THRESHOLD = 5

        fun of(initialColorRes: Int): Brush {
            return Brush().also { it.setColor(initialColorRes) }
        }
    }
}
