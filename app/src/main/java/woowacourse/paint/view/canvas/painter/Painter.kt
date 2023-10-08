package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import woowacourse.paint.view.palette.color.PaletteColor
import woowacourse.paint.view.palette.PaletteMode
import woowacourse.paint.view.palette.shape.PaletteShape

interface Painter {
    fun setPaletteColor(paletteColor: PaletteColor): Painter
    fun setThickness(thickness: Float): Painter
    fun onActionDown(x: Float, y: Float)
    fun onActionMove(x: Float, y: Float)
    fun draw(canvas: Canvas)
    fun extract(): Painter

    fun changePainter(
        paletteMode: PaletteMode,
        paletteShape: PaletteShape = PaletteShape.values().first(),
    ): Painter {
        return when (paletteMode) {
            PaletteMode.BRUSH -> BrushPainter()
            PaletteMode.SHAPE -> getShapePainter(paletteShape)
            PaletteMode.ERASER -> PathEraserPainter()
        }
    }

    private fun getShapePainter(
        paletteShape: PaletteShape,
    ): Painter = when (paletteShape) {
        PaletteShape.RECTANGLE -> RectanglePainter(paletteShape)
        PaletteShape.CIRCLE -> CirclePainter(paletteShape)
    }
}
