package woowacourse.paint.ui.model

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class DrawingToolModel(@DrawableRes val image: Int, val paint: Paint) {
    PEN(
        R.drawable.ic_pen_100,
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        },
    ),
    HIGHLIGHTER(
        R.drawable.ic_highlighter_100,
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.SQUARE
            strokeJoin = Paint.Join.BEVEL
            isAntiAlias = true
        },
    ),
    CIRCLE(
        R.drawable.ic_circle_100,
        Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        },
    ),
    RECTANGLE(
        R.drawable.ic_rectangle_100,
        Paint().apply {
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        },
    ),
    ERASER(
        R.drawable.ic_eraser_100,
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            isAntiAlias = true
        },
    ),
}
