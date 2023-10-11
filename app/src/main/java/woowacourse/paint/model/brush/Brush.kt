package woowacourse.paint.model.brush

import android.graphics.Paint
import androidx.annotation.ColorInt

sealed class Brush {
    abstract val paintInstance: Paint
    abstract fun updateStyle(paint: Paint)

    abstract fun onActionDown(xCursor: Float, yCursor: Float)

    abstract fun onActionMove(xCursor: Float, yCursor: Float)

    abstract fun onActionUp(xCursor: Float, yCursor: Float)

    abstract fun updateColor(@ColorInt color: Int)

    abstract fun updateThickness(thickness: Float)
    fun copyPaint(): Paint = Paint().apply { set(paintInstance) }
}
