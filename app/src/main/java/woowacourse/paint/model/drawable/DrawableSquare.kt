package woowacourse.paint.model.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class DrawableSquare(
    val rect: RectF,
    override val paint: Paint,
) : DrawableElement {

    override fun drawCurrent(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    override fun startDrawing(x: Float, y: Float): DrawableSquare {
        return DrawableSquare(
            rect = RectF(x, y, x, y),
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

    companion object {
        fun from(paint: Paint) = DrawableSquare(
            rect = RectF(),
            paint = Paint(paint).apply { style = Paint.Style.FILL },
        )
    }
}
