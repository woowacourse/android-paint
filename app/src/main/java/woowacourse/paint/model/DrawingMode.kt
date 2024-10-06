package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Xfermode
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.paint.R

enum class DrawingMode(
    @DrawableRes val icon: Int,
    @StringRes val modeName: Int,
    val paintStyle: Style,
    val xfermode: Xfermode?,
) {
    PEN(
        R.drawable.icon_pencil,
        R.string.drawing_mode_pen,
        Style.STROKE,
        null,
    ),
    CIRCLE(
        R.drawable.icon_circle_border,
        R.string.drawing_mode_circle,
        Style.FILL,
        null,
    ),
    RECTANGLE
        (
        R.drawable.icon_rect_border,
        R.string.drawing_mode_rect,
        Style.FILL,
        null,
    ),
    ERASE(
        R.drawable.icon_erase,
        R.string.drawing_mode_erase,
        Style.STROKE,
        PorterDuffXfermode(
            PorterDuff.Mode.CLEAR
        ),
    ), ;

    companion object {
        fun getList(): List<DrawingMode> = entries

        fun DrawingMode.setPaintStyle(paint: Paint) {
            paint.style = this.paintStyle
            paint.xfermode = this.xfermode
        }
    }
}
