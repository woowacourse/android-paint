package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import kotlin.math.abs

class LineBrush(
    private val path: Path = Path(),
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
        val newPaint = Paint(paint).apply {
            this.color = color ?: paint.color
            this.strokeWidth = width ?: paint.strokeWidth
        }
        return LineBrush(Path(), newPaint)
    }

    override fun getBounds(): RectF {
        val bounds = RectF()
        path.computeBounds(bounds, true)
        return bounds
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
