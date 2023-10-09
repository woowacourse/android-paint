package woowacourse.paint.view.canvas.painter

import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.common.softPainter
import woowacourse.paint.view.palette.color.PaletteColor

abstract class ShapePainter(paint: Paint) : Painter(paint) {
    protected var startX: Float = -1F
    protected var startY: Float = -1F
    protected val shapeRange = RectF()

    companion object {
        fun getDefaultPaint(
            paletteColor: PaletteColor = PaletteColor.values().first(),
        ): Paint = Paint().softPainter(
            paletteColor = paletteColor,
            paintStyle = Paint.Style.FILL,
        )
    }
}
