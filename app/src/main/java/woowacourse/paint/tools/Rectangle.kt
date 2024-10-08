package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(val rectF: RectF = RectF(), val paint: Paint = createDefaultPaint()) : DrawingTool {
    override fun initialize(): DrawingTool = Rectangle()

    override fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    ) {
        rectF.apply {
            left = x
            top = y
            right = x
            bottom = y
        }
        paint.color = newPaint.color
    }

    override fun draw(
        x: Float,
        y: Float,
    ) {
        rectF.apply {
            right = x
            bottom = y
        }
    }

    override fun renderOnCanvas(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }

    private companion object {
        fun createDefaultPaint(): Paint {
            return Paint().apply {
                style = Paint.Style.FILL
            }
        }
    }
}
