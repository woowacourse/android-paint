package woowacourse.paint.presentation.ui.canvas.shape

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette

class Rectangle(palette: Palette) : PaintTool(palette, null) {

    private var preX = INITIAL_X
    private var preY = INITIAL_Y

    init {
        palette.paint.style = Paint.Style.FILL
    }

    override fun changePalette(palette: Palette): PaintTool = Rectangle(palette)

    override fun nextPath(): PaintTool = Rectangle(this.palette)

    override fun onDownEvent(pointX: Float, pointY: Float) {
        preX = pointX
        preY = pointY
        path.moveTo(pointX, pointY)
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.reset()
        val left = minOf(preX, pointX)
        val top = minOf(preY, pointY)
        val bottom = maxOf(preY, pointY)
        val right = maxOf(preX, pointX)
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }

    companion object {
        private const val INITIAL_X = 0f
        private const val INITIAL_Y = 0f
    }
}
