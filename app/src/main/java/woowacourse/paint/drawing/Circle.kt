package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.drawing.Drawing2.Companion.DEFAULT_BRUSH_CAP
import woowacourse.paint.drawing.Drawing2.Companion.DEFAULT_BRUSH_STYLE

data class Circle(
    private val rect: RectF = RectF(),
    private val paint: Paint,
) : Drawing2 {
    override fun setUpDefaultPaint() {
        paint.apply {
            style = DEFAULT_BRUSH_STYLE
            strokeCap = DEFAULT_BRUSH_CAP
            isAntiAlias = true
        }
    }

    override fun drawOn(canvas: Canvas) {
        // 원의 중심 및 반지름 계산
        val centerX = (rect.left + rect.right) / 2
        val centerY = (rect.top + rect.bottom) / 2
        val radius = Math.min(rect.width(), rect.height()) / 2

        // 원 그리기
        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    override fun setStartPoint(
        x: Float,
        y: Float,
    ) {
        rect.left = x
        rect.top = y
    }

    override fun pathLineTo(
        x: Float,
        y: Float,
    ) {
        rect.right = x
        rect.bottom = y
    }

    override fun copyWithPaint(thickness: Float): Drawing2 {
        val newPaint =
            Paint(paint).apply {
                strokeWidth = thickness
            }
        return Circle(RectF(rect), newPaint)
    }

    override fun copyWithPaint(color: Int): Drawing2 {
        val newPaint =
            Paint(paint).apply {
                this.color = color
            }
        return Circle(RectF(rect), newPaint)
    }

    override fun copyPoint(
        pointX: Float,
        pointY: Float,
    ): Drawing2 = this.copy(rect = RectF(pointX, pointY, pointX, pointY))

    companion object {
        fun default(): Circle {
            val paint =
                Paint().apply {
                    strokeCap = DEFAULT_BRUSH_CAP
                    isAntiAlias = true
                }
            return Circle(RectF(), paint)
        }
    }
}
