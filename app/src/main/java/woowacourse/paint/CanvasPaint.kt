package woowacourse.paint

import android.graphics.Paint
import androidx.annotation.ColorInt

data class CanvasPaint(
    @ColorInt private val colorInt: Int,
    private val brushWidth: Float,
) : Paint() {
    init {
        style = Style.STROKE
        isAntiAlias = true
        strokeJoin = Join.ROUND
        strokeCap = Cap.ROUND
        strokeWidth = brushWidth
        color = colorInt
    }

    fun changeColor(
        @ColorInt colorInt: Int,
    ): CanvasPaint {
        return this.copy(colorInt = colorInt)
    }

    fun changeBrushWidth(brushWidth: Float): CanvasPaint {
        return this.copy(brushWidth = brushWidth)
    }
}
