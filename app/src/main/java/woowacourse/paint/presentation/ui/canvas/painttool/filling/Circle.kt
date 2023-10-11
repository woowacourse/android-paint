package woowacourse.paint.presentation.ui.canvas.painttool.filling

import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTool

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
