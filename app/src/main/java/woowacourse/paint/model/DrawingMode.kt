package woowacourse.paint.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.paint.R

enum class DrawingMode(
    @DrawableRes val icon: Int,
    @StringRes val modeName: Int,
) {
    PEN(
        R.drawable.icon_pencil,
        R.string.drawing_mode_pen,
    ),
    CIRCLE(
        R.drawable.icon_circle_border,
        R.string.drawing_mode_circle,
    ),
    RECTANGLE
        (
        R.drawable.icon_rect_border,
        R.string.drawing_mode_rect,
    ),
    ERASE(
        R.drawable.icon_erase,
        R.string.drawing_mode_erase,
    ), ;

    companion object {
        fun getList(): List<DrawingMode> = entries
    }
}
