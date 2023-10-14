package woowacourse.paint.custom.view.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

data class Line(
    val type: BrushTypeUiModel,
    val path: Path,
    val paint: Paint,
) : Drawable {

    init {
        when (type) {
            BrushTypeUiModel.ERASER -> { paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
            else -> {}
        }
    }

    private var lastX = 0f
    private var lastY = 0f

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun startDrawing(x: Float, y: Float, paint: Paint): Line {
        path.moveTo(x, y)
        lastX = x
        lastY = y

        return Line(BrushTypeUiModel.PEN, Path(), paint)
    }

    override fun keepDrawing(x: Float, y: Float) {
        path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
        lastX = x
        lastY = y
    }
}
