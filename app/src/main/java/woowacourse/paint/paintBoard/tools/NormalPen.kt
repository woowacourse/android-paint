package woowacourse.paint.paintBoard.tools

import android.graphics.Paint
import woowacourse.paint.paintBoard.Brush
import woowacourse.paint.paintBoard.Line


class NormalPen(
    reset: () -> Unit,
    private val onSave: (Line) -> Unit,
    override val line: Line = Line(brush = Brush(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeMiter = SOFT_ANGLE
            isAntiAlias = true
        }
    ))
) : DrawableTool(line, reset) {

    override fun startPainting(pointX: Float, pointY: Float) {
        onSave(line)
        line.path.moveTo(pointX, pointY)
        line.path.lineTo(pointX, pointY)
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        line.path.lineTo(pointX, pointY)
    }

    companion object {
        private const val SOFT_ANGLE = 0f
    }
}