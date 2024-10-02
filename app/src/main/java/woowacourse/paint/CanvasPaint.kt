package woowacourse.paint

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

data class CanvasPaint(@ColorInt val colorInt: Int, val brushWidth: Float = DEFAULT_BRUSH_SIZE) : Paint() {

    init {
        style = Style.STROKE
        isAntiAlias = true
        strokeJoin = Join.ROUND
        strokeCap = Cap.ROUND
        strokeWidth = brushWidth
    }


    @SuppressLint("ResourceAsColor")
    fun changeColor(@ColorInt colorInt: Int):CanvasPaint {
        return this.copy().apply { color = colorInt }
    }

    fun changeBrushWidth(brushWidth: Float):CanvasPaint {
        return this.copy().apply { strokeWidth = brushWidth }
    }


    companion object {
        private const val DEFAULT_BRUSH_SIZE = 50f
    }

}