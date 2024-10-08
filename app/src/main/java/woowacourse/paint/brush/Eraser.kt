package woowacourse.paint.brush

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.CanvasPaint

class Eraser(override val paint: CanvasPaint) : Brush() {
    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paint.style = Paint.Style.STROKE
    }

    fun changeBrushWidth(width: Float): Eraser {
        return Eraser(paint.changeBrushWidth(width))
    }

    override fun startDraw(
        pointX: Float,
        pointY: Float,
    ): Eraser {
        return Eraser(paint).apply {
            moveTo(pointX, pointY)
        }
    }

    override fun moveBrush(
        pointX: Float,
        pointY: Float,
    ) {
        lineTo(pointX, pointY)
    }
}
