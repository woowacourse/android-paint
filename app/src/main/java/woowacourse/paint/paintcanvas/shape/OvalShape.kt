package woowacourse.paint.paintcanvas.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class OvalShape(var oval: RectF = RectF()) : Shape {
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
        oval = RectF(startX, startY, pointX, pointY)
    }

    override fun onActionUp(
        pointX: Float,
        pointY: Float,
    ) {
        oval = RectF(startX, startY, pointX, pointY)
    }

    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.apply { style = Paint.Style.FILL }
        canvas.drawOval(oval, paint)
    }
}
