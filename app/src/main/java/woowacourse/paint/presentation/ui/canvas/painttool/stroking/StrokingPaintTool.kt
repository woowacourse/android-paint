package woowacourse.paint.presentation.ui.canvas.painttool.stroking

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.presentation.ui.canvas.Palette
import woowacourse.paint.presentation.ui.canvas.painttool.PaintTool

abstract class StrokingPaintTool(
    final override val palette: Palette = Palette(),
) : PaintTool {

    private val path: Path = Path()

    init {
        palette.paint.style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, palette.paint)
    }

    override fun onDownEvent(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    override fun onMoveEvent(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }
}
