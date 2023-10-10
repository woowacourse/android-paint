package woowacourse.paint.presentation.ui.canvas.shape

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PorterDuffXfermode
import woowacourse.paint.presentation.ui.canvas.Palette

abstract class PaintTool(
    val palette: Palette = Palette(),
    porterDuffXfermode: PorterDuffXfermode?,
) {
    protected val path: Path = Path()

    init {
        palette.paint.xfermode = porterDuffXfermode
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, palette.paint)
    }

    abstract fun nextPath(): PaintTool

    abstract fun changePalette(palette: Palette): PaintTool

    abstract fun onMoveEvent(pointX: Float, pointY: Float)

    abstract fun onDownEvent(pointX: Float, pointY: Float)
}
