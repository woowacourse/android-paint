package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper

@Parcelize
data class Eraser(
    override val startX: Float,
    override val startY: Float,
    override val paint: PaintWrapper,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private val path =
        Path().apply {
            moveTo(startX, startY)
        }
    private val eraserPaint: Paint =
        paint.toPaint().apply {
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

    init {
        val paint = this.paint.toPaint()
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, eraserPaint)
    }
}
