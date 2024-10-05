package woowacourse.paint.paintcanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class RectShape(var rect: RectF = RectF()) : Shape {
    private var startX = 0F
    private var startY = 0F

    override fun onActionDown(
        pointX: Float,
        pointY: Float,
    ) {
        startX = pointX
        startY = pointY
    }

    override fun onActionMove(
        pointX: Float,
        pointY: Float,
    ) {
        rect = RectF(startX, startY, pointX, pointY)
    }

    override fun onActionUp(
        pointX: Float,
        pointY: Float,
    ) {
        rect = RectF(startX, startY, pointX, pointY)
    }

    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.apply { style = Paint.Style.FILL }
        canvas.drawRect(rect, paint)
    }
}
