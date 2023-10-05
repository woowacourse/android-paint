package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path
import kotlin.math.abs

class LineBrush private constructor(
    override val path: Path = Path(),
    override val paint: Paint = Paint(),
) : Brush {

    private var prevX: Float = 0f
    private var prevY: Float = 0f

    init {
        setupPaint()
    }

    override fun setColor(color: Int) {
        paint.color = color
    }

    override fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    override fun startDrawing(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        prevX = x
        prevY = y
    }

    override fun keepDrawing(x: Float, y: Float) {
        if (abs(x - prevX) >= MOVE_THRESHOLD || abs(y - prevY) >= MOVE_THRESHOLD) {
            path.quadTo(prevX, prevY, (x + prevX) / 2, (y + prevY) / 2)
            prevX = x
            prevY = y
        }
    }

    override fun finishDrawing() {
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

        fun of(initialColorRes: Int): LineBrush {
            return LineBrush().also { it.setColor(initialColorRes) }
        }
    }
}
