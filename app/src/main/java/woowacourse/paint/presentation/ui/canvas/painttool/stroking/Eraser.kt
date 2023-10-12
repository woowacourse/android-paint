package woowacourse.paint.presentation.ui.canvas.painttool.stroking

import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.presentation.ui.canvas.Palette
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTool

class Eraser(palette: Palette) : StrokingPaintTool(palette) {

    init {
        palette.paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun nextPath(): PaintTool = Eraser(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Eraser(palette ?: this.palette.copy())
    }
}
