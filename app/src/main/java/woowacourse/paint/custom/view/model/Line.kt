package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

data class Line(
    val type: BrushTypeUiModel,
    val x: Float,
    val y: Float,
    val paint: Paint,
) : Drawable {

    private val path = Path()
    private var lastX = 0f
    private var lastY = 0f

    init {
        when (type) {
            BrushTypeUiModel.ERASER -> { paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
            else -> {}
        }
        path.moveTo(x, y)
        lastX = x
        lastY = y
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun keepDrawing(x: Float, y: Float) {
        path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
        lastX = x
        lastY = y
    }
}
