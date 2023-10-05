package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

class RectangleBrush(
    override val path: Path = Path(),
    override val paint: Paint = Paint(),
) : Brush {

    private var startX: Float = 0f
    private var startY: Float = 0f

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
        startX = x
        startY = y
    }

    override fun keepDrawing(x: Float, y: Float) {
        path.reset()
        val left = if (startX <= x) startX else x
        val right = if (startX <= x) x else startX
        val top = if (startY <= y) startY else y
        val bottom = if (startY <= y) y else startY
        path.addRect(left, top, right, bottom, Path.Direction.CCW)
    }

    private fun setupPaint() {
        paint.style = Paint.Style.FILL
    }

    override fun finishDrawing() = Unit
}
