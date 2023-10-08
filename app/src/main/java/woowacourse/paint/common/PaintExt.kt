package woowacourse.paint.common

import android.graphics.Paint
import woowacourse.paint.view.palette.color.PaletteColor

fun Paint.softPainter(
    paletteColor: PaletteColor = PaletteColor.RED,
    paintStyle: Paint.Style = Paint.Style.STROKE,
): Paint = apply {
    isAntiAlias = true
    style = paintStyle
    strokeJoin = Paint.Join.ROUND
    strokeCap = Paint.Cap.ROUND
    color = paletteColor.color
}
