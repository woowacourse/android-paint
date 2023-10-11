package woowacourse.paint.presentation.ui.canvas.painttool

import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette

class Circle(palette: Palette) : FillingPaintTool(palette) {

    override fun nextPath(): PaintTool = Circle(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Circle(palette ?: this.palette.copy())
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.reset()
        path.addOval(preX, preY, pointX, pointY, Path.Direction.CW)
    }
}
