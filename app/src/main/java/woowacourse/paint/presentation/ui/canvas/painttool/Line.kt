package woowacourse.paint.presentation.ui.canvas.painttool

import woowacourse.paint.presentation.ui.canvas.Palette

class Line(palette: Palette) : StrokePaintTool(palette) {

    override fun nextPath(): PaintTool = Line(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Line(palette ?: this.palette.copy())
    }
}
