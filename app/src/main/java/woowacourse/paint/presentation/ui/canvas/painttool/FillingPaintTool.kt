package woowacourse.paint.presentation.ui.canvas.painttool

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette

abstract class FillingPaintTool(
    final override val palette: Palette = Palette(),
) : PaintTool {

    protected val path: Path = Path()
    protected var preX = INITIAL_X
    protected var preY = INITIAL_Y

    init {
        palette.paint.xfermode = null
        palette.paint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, palette.paint)
    }

    override fun onDownEvent(pointX: Float, pointY: Float) {
        preX = pointX
        preY = pointY
        path.moveTo(pointX, pointY)
    }

    companion object {
        private const val INITIAL_X = 0f
        private const val INITIAL_Y = 0f
    }
}
