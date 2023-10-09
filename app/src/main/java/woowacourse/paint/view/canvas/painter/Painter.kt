package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.view.palette.PaletteMode
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.shape.PaletteShape

abstract class Painter(protected val paint: Paint) {
    abstract fun setPaletteColor(paletteColor: PaletteColor): Painter
    abstract fun setThickness(thickness: Float): Painter
    abstract fun onActionDown(x: Float, y: Float)
    abstract fun onActionMove(x: Float, y: Float)
    abstract fun draw(canvas: Canvas)
    abstract fun extract(): Painter

    fun changePainter(
        paletteMode: PaletteMode,
        paletteShape: PaletteShape = PaletteShape.values().first(),
        paletteColor: PaletteColor = PaletteColor.values().first(),
        thickness: Float,
    ): Painter = when (paletteMode) {
        PaletteMode.BRUSH -> BrushPainter(paletteColor = paletteColor, thickness = thickness)
        PaletteMode.SHAPE -> getShapePainter(paletteShape, paletteColor)
        PaletteMode.ERASER -> PathEraserPainter(thickness = thickness)
    }

    private fun getShapePainter(
        paletteShape: PaletteShape,
        paletteColor: PaletteColor,
    ): Painter = when (paletteShape) {
        PaletteShape.RECTANGLE -> RectanglePainter(paletteShape, paletteColor)
        PaletteShape.CIRCLE -> CirclePainter(paletteShape, paletteColor)
    }
}
