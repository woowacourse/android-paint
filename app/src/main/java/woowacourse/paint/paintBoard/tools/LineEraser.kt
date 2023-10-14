package woowacourse.paint.paintBoard.tools

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.paintBoard.Brush
import woowacourse.paint.paintBoard.Line

class LineEraser(
    private val onRemove: (Line) -> Unit,
    private val line: Line = Line(brush = Brush(
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeMiter = SOFT_ANGLE
            isAntiAlias = true
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }
    ))
) : EraseTool() {

    fun setWidth(width: Float) {
        line.brush.changeBrushWidth(width)
    }

    override fun startPainting(pointX: Float, pointY: Float) {
        onRemove(line)
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
