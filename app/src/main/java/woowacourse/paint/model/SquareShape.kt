package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

class SquareShape(
    private val startX: Float,
    private val startY: Float,
    private var endX: Float,
    private var endY: Float,
) : Shape {
    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawRect(startX, startY, endX, endY, paint)
    }

    override fun update(
        x: Float,
        y: Float,
    ) {
        endX = x
        endY = y
    }
}
