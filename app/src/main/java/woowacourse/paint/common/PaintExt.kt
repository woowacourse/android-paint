package woowacourse.paint.common

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.view.palette.color.PaletteColor

fun Paint.softPainter(
    paletteColor: PaletteColor = PaletteColor.RED,
    paintStyle: Paint.Style = Paint.Style.STROKE,
    porterDuffMode: PorterDuff.Mode = PorterDuff.Mode.SRC_OVER,
): Paint = apply {
    isAntiAlias = true
    style = paintStyle
    strokeJoin = Paint.Join.ROUND
    strokeCap = Paint.Cap.ROUND
    color = paletteColor.color
    xfermode = PorterDuffXfermode(porterDuffMode)
}
