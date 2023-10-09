package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

class Eraser(
    val onSave: (line: Line) -> Unit,
    override val line: Line
) : Tools {

    override fun startPainting(pointX: Float, pointY: Float) {
        TODO("Not yet implemented")
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        TODO("Not yet implemented")
    }

    override fun finishPainting() {
        TODO("Not yet implemented")
    }
}
