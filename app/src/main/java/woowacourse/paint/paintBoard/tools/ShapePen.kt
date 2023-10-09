package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

abstract class ShapePen(
    open val onSave: (Line) -> Unit,
) : Tools {
    var startingPointX: Float = 0f
    var startingPointY: Float = 0f

    override fun startPainting(pointX: Float, pointY: Float) {
        startingPointX = pointX
        startingPointY = pointY
    }

    override fun drawPainting(pointX: Float, pointY: Float) {
        draw(pointX, pointY)
    }

    override fun finishPainting() {
        onSave(line)
    }

    abstract fun draw(pointX: Float, pointY: Float)
}
