package woowacourse.paint.presentation.ui.canvas.painttool

import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette

class Rectangle(palette: Palette) : FillingPaintTool(palette) {

    override fun nextPath(): PaintTool = Rectangle(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Rectangle(palette ?: this.palette.copy())
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.reset()
        val left = minOf(preX, pointX)
        val top = minOf(preY, pointY)
        val bottom = maxOf(preY, pointY)
        val right = maxOf(preX, pointX)
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }
}
