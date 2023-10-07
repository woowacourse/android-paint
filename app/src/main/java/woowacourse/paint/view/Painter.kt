package woowacourse.paint.view

import android.graphics.Canvas
import android.graphics.Paint

abstract class Painter(val paint: Paint) {
    abstract fun onTouchDown(x: Float, y: Float)
    abstract fun onTouchMove(x: Float, y: Float)
    abstract fun onTouchUp(x: Float, y: Float)
    abstract fun draw(canvas: Canvas)
    abstract fun setPaletteColor(paletteColor: PaletteColor): Painter
    abstract fun setThickness(thickness: Float): Painter

    fun changePainter(paletteMode: PaletteMode): Painter {
        return when (paletteMode) {
            PaletteMode.BRUSH -> BrushPainter(paint = paint)
            else -> throw IllegalArgumentException("")
            // PaletteMode.SHAPE -> ShapePainter(paint)
            // PaletteMode.ERASER -> EraserPainter(paint)
        }
    }
}
