package woowacourse.paint.presentation.ui.canvas.painttool.stroking

import woowacourse.paint.presentation.ui.canvas.Palette
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTool

class Line(palette: Palette) : StrokingPaintTool(palette) {

    override fun nextPath(): PaintTool = Line(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Line(palette ?: this.palette.copy())
    }
}
