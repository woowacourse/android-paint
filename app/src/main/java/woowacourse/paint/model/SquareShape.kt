package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

class SquareShape(
    private val startX: Float = 0f,
    private val startY: Float = 0f,
    private var endX: Float = 0f,
    private var endY: Float = 0f,
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
