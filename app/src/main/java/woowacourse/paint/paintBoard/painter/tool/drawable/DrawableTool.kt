package woowacourse.paint.paintBoard.painter.tool.drawable

import woowacourse.paint.paintBoard.Line
import woowacourse.paint.paintBoard.painter.Painter

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

