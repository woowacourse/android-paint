package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import kotlin.math.pow
import kotlin.math.sqrt

class CircleBrush(
    override val path: Path = Path(),
    private val paint: Paint = Paint(),
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
        val dx = startX - x
        val dy = startY - y
        val radius = sqrt(dx.pow(2).toDouble() + dy.pow(2).toDouble()).toFloat() / 2
        path.addCircle((startX + x) / 2, (startY + y) / 2, radius, Path.Direction.CCW)
    }

    override fun finishDrawing() = Unit

    override fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun copy(color: Int?, width: Float?): Brush {
        val newPaint = defaultPaint.apply { this.color = color ?: paint.color }
        return CircleBrush(Path(), newPaint)
    }

    companion object {
        private val defaultPaint
            get() = Paint().apply {
                style = Paint.Style.FILL
            }
    }
}
