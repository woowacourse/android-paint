package woowacourse.paint.tool

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import java.lang.Float.max
import kotlin.math.abs

class Circle(private val circle: RectF = RectF()) : Tool {
    private val centerX
        get() = (circle.left + circle.right) / 2
    private val centerY
        get() = (circle.top + circle.bottom) / 2
    private val radius
        get() = max(abs(circle.left - centerX), abs(circle.top - centerY))

    override fun copy(): Tool {
        return Circle()
    }

    override fun startDraw(pointX: Float, pointY: Float) {
        circle.top = pointY
        circle.bottom = pointY
        circle.left = pointX
        circle.right = pointX
    }

    override fun onDraw(pointX: Float, pointY: Float) {
        circle.bottom = pointY
        circle.right = pointX
    }

    override fun drawPath(canvas: Canvas, paint: Paint) {
        canvas.drawCircle(centerX, centerY, radius, paint)
    }
}
