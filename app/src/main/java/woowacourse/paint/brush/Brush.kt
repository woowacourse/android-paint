package woowacourse.paint.brush

import android.graphics.Canvas
import android.graphics.Path

interface Brush {
    val path: Path
    fun startDrawing(x: Float, y: Float)
    fun keepDrawing(x: Float, y: Float)
    fun finishDrawing()
    fun drawPath(canvas: Canvas)
    fun copy(color: Int? = null, width: Float? = null): Brush
}
