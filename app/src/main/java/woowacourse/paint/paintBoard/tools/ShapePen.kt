package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

abstract class ShapePen(
    private val reset: () -> Unit,
    open val onSave: (Line) -> Unit,
) : Tools {
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

    override fun finishPainting() {
        reset.invoke()
    }

    abstract fun draw(pointX: Float, pointY: Float)
}
