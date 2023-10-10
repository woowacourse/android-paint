package woowacourse.paint.presentation.ui.canvas.shape

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette

class Circle(
    palette: Palette,
) : PaintTool(palette, null) {

    private var preX = 0f
    private var preY = 0f

    init {
        palette.paint.style = Paint.Style.FILL
    }

    override fun changePalette(palette: Palette): PaintTool = Circle(palette)

    override fun nextPath(): PaintTool = Circle(this.palette)

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.reset()
        path.addOval(preX, preY, pointX, pointY, Path.Direction.CW)
    }

    override fun onDownEvent(pointX: Float, pointY: Float) {
        preX = pointX
        preY = pointY
        path.moveTo(pointX, pointY)
    }
}
