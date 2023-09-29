package woowacourse.paint

import android.graphics.Paint
import androidx.annotation.DrawableRes

enum class DrawingTool(@DrawableRes val image: Int, val paint: Paint) {
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
            strokeJoin = Paint.Join.MITER
            isAntiAlias = true
        },
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
