package woowacourse.paint.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.paint.R

enum class DrawingMode(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int,
) {
    PEN(R.drawable.ic_pen, R.string.drawing_mode_pen),
    SQUARE(R.drawable.ic_square, R.string.drawing_mode_square),
    CIRCLE(R.drawable.ic_circle, R.string.drawing_mode_circle),
    ERASER(R.drawable.ic_eraser, R.string.drawing_mode_eraser), ;

    companion object {
        fun getDrawingModes(): List<DrawingMode> = entries
    }
}
