package woowacourse.paint.presentation.ui.canvas.shape

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.presentation.ui.canvas.Palette

class Eraser(
    palette: Palette,
) : PaintTool(palette, PorterDuffXfermode(PorterDuff.Mode.CLEAR)) {

    init {
        palette.paint.style = Paint.Style.STROKE
    }

    override fun nextPath(): PaintTool = Eraser(this.palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Eraser(palette ?: this.palette.copy())
    }

    override fun onDownEvent(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }
}
