package woowacourse.paint.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Rectangle(private val rect: RectF = RectF()) : Shape {
    override fun copy(): Shape {
        return Rectangle()
    }

    override fun startDraw(pointX: Float, pointY: Float) {
        rect.top = pointY
        rect.bottom = pointY
        rect.left = pointX
        rect.right = pointX
    }

    override fun onDraw(pointX: Float, pointY: Float) {
        rect.bottom = pointY
        rect.right = pointX
    }

    override fun drawPath(canvas: Canvas, paint: Paint) {
        canvas.drawRect(rect, paint)
    }
}
