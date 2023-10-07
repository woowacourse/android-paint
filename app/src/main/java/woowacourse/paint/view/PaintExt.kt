package woowacourse.paint.view

import android.graphics.Paint

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
