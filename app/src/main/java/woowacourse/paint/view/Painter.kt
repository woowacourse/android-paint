package woowacourse.paint.view

import android.graphics.Canvas

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
            PaletteMode.SHAPE -> {
                when (paletteShape) {
                    PaletteShape.RECTANGLE -> RectanglePainter(paletteShape)
                    PaletteShape.CIRCLE -> CirclePainter(paletteShape)
                }
            }

            else -> throw IllegalArgumentException("")
            // PaletteMode.ERASER -> EraserPainter(paint)
        }
    }
}
