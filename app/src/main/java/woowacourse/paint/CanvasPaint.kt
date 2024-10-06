package woowacourse.paint

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Xfermode
import androidx.annotation.ColorInt

data class CanvasPaint(
    @ColorInt private val colorInt: Int,
    private val brushWidth: Float,
    private val paintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_OVER
) : Paint() {


    init {
        style = Style.STROKE
        isAntiAlias = true
        strokeJoin = Join.ROUND
        strokeCap = Cap.ROUND
        strokeWidth = brushWidth
        color = colorInt
        xfermode = PorterDuffXfermode(paintMode)
    }

    fun changeColor(
        @ColorInt colorInt: Int,
    ): CanvasPaint {
        return this.copy(colorInt = colorInt)
    }

    fun changeBrushWidth(brushWidth: Float): CanvasPaint {
        return this.copy(brushWidth = brushWidth)
    }

    fun changeToEraseMode(): CanvasPaint {
        return this.copy(paintMode = PorterDuff.Mode.CLEAR)
    }

    fun changeColorMode(): CanvasPaint {
        return this.copy(paintMode = PorterDuff.Mode.SRC_OVER)
    }

    fun isEraseMode(): Boolean {
        return paintMode == PorterDuff.Mode.CLEAR
    }

}
