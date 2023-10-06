package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class DrawableSquare(
    private val rect: RectF = RectF(),
    override val paint: Paint,
) : DrawableElement {

    init {
        paint.apply { style = Paint.Style.FILL }
    }

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    override fun startDrawing(x: Float, y: Float): DrawableSquare {
        return DrawableSquare(
            RectF(x, y, x, y),
            paint = Paint(paint),
        )
    }

    override fun keepDrawing(x: Float, y: Float) {
        rect.right = x
        rect.bottom = y
    }

    override fun changePaintColor(color: Int): DrawableSquare {
        return copy(
            paint = Paint(paint).apply { this.color = color },
        )
    }
}
