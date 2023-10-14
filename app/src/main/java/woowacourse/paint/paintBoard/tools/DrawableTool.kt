package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

abstract class DrawableTool(
    open val line: Line,
    private val reset: () -> Unit
) : Painter {

    fun setWidth(width: Float) {
        line.brush.changeBrushWidth(width)
    }

    fun setColor(color: Int) {
        line.brush.changeBrushColor(color)
    }

    override fun finishPainting() {
        reset.invoke()
    }
}

