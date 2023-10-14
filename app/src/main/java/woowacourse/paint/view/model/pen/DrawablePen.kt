package woowacourse.paint.view.model.pen

import woowacourse.paint.view.model.pen.ink.Ink

interface DrawablePen : Pen {
    val ink: Ink
    fun setColor(color: Int)
    fun cacheCurrentPaint()
}
