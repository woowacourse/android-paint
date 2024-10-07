package woowacourse.paint.view.shape

import android.graphics.Canvas
import android.graphics.Path
import kotlinx.parcelize.Parcelize
import woowacourse.paint.utils.PaintWrapper

@Parcelize
data class Pencil(
    override val startX: Float,
    override val startY: Float,
    override val paint: PaintWrapper,
    override val strokeWidth: Float,
) : BrushShape(startX, startY, paint, strokeWidth) {
    private val path =
        Path().apply {
            moveTo(startX, startY)
        }
    private val pencilPaint = paint.toPaint()

    override fun updatePosition(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, pencilPaint)
    }
}
