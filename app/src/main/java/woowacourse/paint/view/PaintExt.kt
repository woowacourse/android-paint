package woowacourse.paint.view

import android.graphics.Paint

fun Paint.softPainter(
    paletteColor: PaletteColor = PaletteColor.RED,
): Paint = apply {
    isAntiAlias = true
    style = Paint.Style.STROKE
    strokeJoin = Paint.Join.ROUND
    strokeCap = Paint.Cap.ROUND
    color = paletteColor.color
}
