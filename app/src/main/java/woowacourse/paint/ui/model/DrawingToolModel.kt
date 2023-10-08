package woowacourse.paint.ui.model

import android.graphics.Paint
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
    SHAPE(
        R.drawable.ic_shape_100,
        Paint(),
    ),
    ERASER(
        R.drawable.ic_eraser_100,
        Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        },
    ),
}
