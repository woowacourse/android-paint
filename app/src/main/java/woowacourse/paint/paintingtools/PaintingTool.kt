package woowacourse.paint.paintingtools

import android.graphics.Paint

abstract class PaintingTool(
    val paintColor: Int,
    val paintWidth: Float,
) {

    abstract val paint: Paint

    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeWidth(width: Float) {
        paint.strokeWidth = width
    }

    abstract fun copy(): PaintingTool
}
