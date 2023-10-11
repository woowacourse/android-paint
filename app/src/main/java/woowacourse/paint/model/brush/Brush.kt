package woowacourse.paint.model.brush

import android.graphics.Paint
import androidx.annotation.ColorInt

sealed interface Brush {
    val paintInstance: Paint
    fun updateStyle(paint: Paint)

    fun onActionDown(xCursor: Float, yCursor: Float)

    fun onActionMove(xCursor: Float, yCursor: Float)

    fun onActionUp(xCursor: Float, yCursor: Float)

    fun updateColor(@ColorInt color: Int)

    fun updateThickness(thickness: Float)
    fun copyPaint(): Paint = Paint().apply { set(paintInstance) }
}
