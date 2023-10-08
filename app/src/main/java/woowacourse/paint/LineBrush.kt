package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.abs

class LineBrush(
    private var path: Path = Path(),
    private var paint: Paint = defaultPaint,
) : Brush {

    private var prevX: Float = 0f
    private var prevY: Float = 0f

    override fun startDrawing(x: Float, y: Float) {
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

    override fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun copy(color: Int?, width: Float?): Brush {
        val newPaint = defaultPaint.apply {
            this.color = color ?: paint.color
            this.strokeWidth = width ?: paint.strokeWidth
        }
        return LineBrush(Path(), newPaint)
    }

    companion object {
        private const val MOVE_THRESHOLD = 5

        private val defaultPaint
            get() = Paint().apply {
                strokeWidth = 10f
                isAntiAlias = true
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
            }
    }
}
