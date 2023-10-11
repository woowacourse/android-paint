package woowacourse.paint.painting

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

sealed class Painting(
    val path: Path,
    val paint: Paint,
) {
    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeWidth(width: Float) {
        paint.strokeWidth = width
    }

    abstract fun onActionDown(x: Float, y: Float)

    abstract fun onActionMove(x: Float, y: Float)

    abstract fun getInitializedPathPainting(): Painting

    abstract fun drawOnCanvas(canvas: Canvas)
}
