package woowacourse.paint.view.model.pen

import android.graphics.Canvas

interface Pen {
    fun startPaint(pointX: Float, pointY: Float)
    fun movePaint(pointX: Float, pointY: Float)
    fun cacheCurrentPaint()
    fun draw(canvas: Canvas)
    fun setStrokeWidth(strokeWidth: Float)
    fun setColor(color: Int)
}
