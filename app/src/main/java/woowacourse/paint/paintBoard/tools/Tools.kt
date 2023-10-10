package woowacourse.paint.paintBoard.tools

import woowacourse.paint.paintBoard.Line

interface Tools : Painter {

    val line: Line

    fun setWidth(width: Float)

    fun setColor(color: Int)
}