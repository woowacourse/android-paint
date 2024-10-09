package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.drawing.Drawing.Companion.DEFAULT_BRUSH_CAP

data class Rectangle(val rect: RectF = RectF(), private val paint: Paint) : Drawing {
    override fun drawOn(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    override fun setStartPoint(
        x: Float,
        y: Float,
    ) {
        rect.apply {
            left = x
            top = y
        }
    }

    override fun pathLineTo(
        x: Float,
        y: Float,
    ) {
        rect.apply {
            right = x
            bottom = y
        }
    }

    override fun copyWithPaint(thickness: Float): Drawing {
        val paint =
            Paint(paint).apply {
                strokeWidth = thickness
            }
        return Rectangle(rect, paint)
    }

    override fun copyWithPaint(color: Int): Drawing {
        val paint =
            Paint(paint).apply {
                this.color = color
            }
        return Rectangle(rect, paint)
    }

    override fun copyPoint(
        pointX: Float,
        pointY: Float,
    ): Drawing = this.copy(rect = RectF(pointX, pointY, pointX, pointY))

    companion object {
        fun default(): Rectangle {
            val paint =
                Paint().apply {
                    strokeCap = DEFAULT_BRUSH_CAP
                    isAntiAlias = true
                }
            return Rectangle(RectF(), paint)
        }
    }
}
