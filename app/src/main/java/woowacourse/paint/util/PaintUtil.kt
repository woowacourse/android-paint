package woowacourse.paint.util

import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt

fun Paint.applyPaintSetting(
    @ColorInt paintColor: Int = Color.RED,
    paintWidth: Float = 50.0f,
    paintStyle: Paint.Style = Paint.Style.STROKE,
    paintCap: Paint.Cap = Paint.Cap.ROUND,
): Paint = this.apply {
    isAntiAlias = true
    xfermode = null
    strokeWidth = paintWidth
    strokeJoin = Paint.Join.ROUND
    style = paintStyle
    strokeCap = paintCap
    color = paintColor
}
