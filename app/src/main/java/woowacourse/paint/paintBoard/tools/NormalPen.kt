package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

class NormalPen(
    private val reset: () -> Unit,
    private val onSave: (Line) -> Unit,
    override val line: Line
) : Tools {

    override fun startPainting(pointX: Float, pointY: Float) {
        onSave(line)
        line.path.moveTo(pointX, pointY)
        line.path.lineTo(pointX, pointY)
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        line.path.lineTo(pointX, pointY)
    }

    override fun finishPainting() {
        reset.invoke()
    }
}