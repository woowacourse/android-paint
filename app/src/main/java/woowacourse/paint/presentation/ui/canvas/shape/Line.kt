package woowacourse.paint.presentation.ui.canvas.shape

import android.graphics.Paint
import woowacourse.paint.presentation.ui.canvas.Palette

class Line(palette: Palette) : PaintTool(palette, null) {

    init {
        palette.paint.style = Paint.Style.STROKE
    }

    override fun nextPath(): PaintTool = Line(palette)

    override fun changePalette(palette: Palette?): PaintTool {
        return Line(palette ?: this.palette.copy())
    }

    override fun onDownEvent(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }
}
