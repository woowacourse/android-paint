package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

interface Brush {
    val path: Path
    val paint: Paint

    fun setColor(color: Int)
    fun setStrokeWidth(width: Float)
    fun startDrawing(x: Float, y: Float)
    fun keepDrawing(x: Float, y: Float)
    fun finishDrawing()
}
