package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.RectF

interface Brush {
    fun startDrawing(x: Float, y: Float)
    fun keepDrawing(x: Float, y: Float)
    fun finishDrawing()
    fun drawPath(canvas: Canvas)
    fun copy(color: Int? = null, width: Float? = null): Brush
    fun getBounds(): RectF
}
