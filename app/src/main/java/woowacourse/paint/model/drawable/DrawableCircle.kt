package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.sqrt

data class DrawableCircle(
    private val cx: Float = 0f,
    private val cy: Float = 0f,
    private var radius: Float = 0f,
    override val paint: Paint,
) : DrawableElement {

    init {
        paint.apply { style = Paint.Style.FILL }
    }

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint)
    }

    override fun startDrawing(x: Float, y: Float): DrawableElement {
        return DrawableCircle(
            cx = x,
            cy = y,
            radius = 0f,
            paint = Paint(paint),
        )
    }

    override fun keepDrawing(x: Float, y: Float) {
        radius = calculateRadius(x, y)
    }

    override fun changePaintColor(color: Int): DrawableElement {
        return copy(
            paint = Paint(paint).apply { this.color = color },
        )
    }

    private fun calculateRadius(x: Float, y: Float): Float {
        val dx = x - cx
        val dy = y - cy
        return sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }
}
