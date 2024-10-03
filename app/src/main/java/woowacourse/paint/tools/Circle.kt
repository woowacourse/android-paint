package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.sqrt

data class Circle(val paint: Paint = createDefaultPaint()) : DrawingTool {
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f

    override fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    ) {
        centerX = x
        centerY = y
        paint.color = newPaint.color
    }

    override fun draw(
        x: Float,
        y: Float,
    ) {
        radius = sqrt(((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)).toDouble()).toFloat()
    }

    override fun renderOnCanvas(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    private companion object {
        fun createDefaultPaint(): Paint {
            return Paint().apply {
                style = Paint.Style.FILL
            }
        }
    }
}
