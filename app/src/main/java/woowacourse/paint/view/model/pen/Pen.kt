package woowacourse.paint.view.model.pen

interface Pen {
    fun startPaint(pointX: Float, pointY: Float)
    fun movePaint(pointX: Float, pointY: Float)
}
