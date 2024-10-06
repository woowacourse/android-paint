package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.paint.R

enum class DrawingMode(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int,
    val paintStyle: Paint.Style,
    val xferMode: PorterDuffXfermode?,
) {
    PEN(R.drawable.ic_pen, R.string.drawing_mode_pen, Paint.Style.STROKE, null),
    SQUARE(R.drawable.ic_square, R.string.drawing_mode_square, Paint.Style.FILL, null),
    CIRCLE(R.drawable.ic_circle, R.string.drawing_mode_circle, Paint.Style.FILL, null),
    ERASER(
        R.drawable.ic_eraser,
        R.string.drawing_mode_eraser,
        Paint.Style.STROKE,
        PorterDuffXfermode(PorterDuff.Mode.CLEAR),
    ), ;

    companion object {
        fun getDrawingModes(): List<DrawingMode> = entries
    }
}
