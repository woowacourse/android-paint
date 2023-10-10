package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape

data class RectanglePainter(
    private val shape: PaletteShape,
    private val paletteColor: PaletteColor = PaletteColor.values().first(),
) : ShapePainter(getDefaultPaint(paletteColor)) {

    override fun setPaletteColor(paletteColor: PaletteColor): Painter = copy(
        paletteColor = paletteColor,
    )

    override fun setThickness(thickness: Float): RectanglePainter = copy()

    override fun onActionDown(x: Float, y: Float) {
        startX = x
        startY = y
        shapeRange.set(startX, startY, x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        shapeRange.set(startX, startY, x, y)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(shapeRange, paint)
    }

    override fun extract(): Painter = copy()
}
