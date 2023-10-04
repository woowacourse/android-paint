package woowacourse.paint.view.model.pen

interface Pen {
    val ink: Ink
    fun startPaint(pointX: Float, pointY: Float)
    fun movePaint(pointX: Float, pointY: Float)
    fun cacheCurrentPaint()
    fun setStrokeWidth(strokeWidth: Float)
    fun setColor(color: Int)
}
