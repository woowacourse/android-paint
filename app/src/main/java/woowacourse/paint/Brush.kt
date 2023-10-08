package woowacourse.paint

import android.graphics.Canvas

interface Brush {
    fun setColor(color: Int): Brush
    fun setStrokeWidth(width: Float): Brush
    fun startDrawing(x: Float, y: Float)
    fun keepDrawing(x: Float, y: Float)
    fun finishDrawing()
    fun drawPath(canvas: Canvas)
}
