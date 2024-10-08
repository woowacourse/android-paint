package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper

@Parcelize
data class Rectangle(
    override val startX: Float,
    override val startY: Float,
    override val paint: PaintWrapper,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private var endX: Float = startX
    private var endY: Float = startY
    private val rectPaint = paint.toPaint()

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        endX = x
        endY = y
    }

    override fun draw(canvas: Canvas) {
        rectPaint.style = Paint.Style.FILL
        canvas.drawRect(startX, startY, endX, endY, rectPaint)
    }
}
