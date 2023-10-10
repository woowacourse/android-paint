package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class RectangleBrush(
    override val path: Path = Path(),
    private val paint: Paint = defaultPaint,
) : Brush {

    private var startX: Float = 0f
    private var startY: Float = 0f

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

    override fun finishDrawing() = Unit

    override fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun copy(color: Int?, width: Float?): Brush {
        val newPaint = Paint(paint).apply { this.color = color ?: paint.color }
        return RectangleBrush(Path(), newPaint)
    }

    companion object {
        private val defaultPaint
            get() = Paint().apply {
                style = Paint.Style.FILL
                isAntiAlias = true
            }
    }
}
