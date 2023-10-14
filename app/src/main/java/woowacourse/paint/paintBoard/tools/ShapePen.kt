package woowacourse.paint.paintBoard.tools

import android.graphics.Paint
import woowacourse.paint.paintBoard.Brush
import woowacourse.paint.paintBoard.Line

abstract class ShapePen(
    reset: () -> Unit,
    private val onSave: (Line) -> Unit,
    override val line: Line = Line(brush = Brush(
        Paint().apply {
            style = Paint.Style.FILL
            isAntiAlias = true
        }
    ))
) : DrawableTool(line, reset) {

    var startingPointX: Float = 0f
    var startingPointY: Float = 0f

    override fun startPainting(pointX: Float, pointY: Float) {
        onSave(line)
        startingPointX = pointX
        startingPointY = pointY
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        draw(pointX, pointY)
    }

    abstract fun draw(pointX: Float, pointY: Float)
}
