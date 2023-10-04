package woowacourse.paint.view.model.pen

import woowacourse.paint.view.model.pen.ink.Ink

interface Pen {
    val ink: Ink
    fun startPaint(pointX: Float, pointY: Float)
    fun movePaint(pointX: Float, pointY: Float)
    fun cacheCurrentPaint()
    fun setStrokeWidth(strokeWidth: Float)
    fun setColor(color: Int)
}
