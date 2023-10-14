package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

interface Tools : Painter {

    val line: Line

    fun setWidth(width: Float)

    fun setColor(color: Int)
}

abstract class DrawableTool(
    open val line: Line
) : Painter {

    fun setWidth(width: Float) {
        line.brush.changeBrushWidth(width)
    }

    fun setColor(color: Int) {
        line.brush.changeBrushColor(color)
    }
}

abstract class EraseTool : Painter