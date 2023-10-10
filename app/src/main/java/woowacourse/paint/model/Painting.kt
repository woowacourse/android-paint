package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt

abstract class Painting {
    abstract val paint: Paint
    abstract fun movePath(x: Float, y: Float): Painting
    abstract fun initPath(prevX: Float, prevY: Float, x: Float, y: Float)
    abstract fun draw(canvas: Canvas)
    open fun setStroke(value: Float): Painting = PenPainting()
    open fun setColor(@ColorInt color: Int): Painting = PenPainting()

    abstract fun getNewPainting(): Painting

    fun setPaintBrush(brushTool: BrushTool): Painting {
        return when (brushTool) {
            BrushTool.PEN -> PenPainting(_paint = Paint(paint))
            BrushTool.RECTANGLE -> RectanglePainting(_paint = Paint(paint))
            BrushTool.CIRCLE -> CirclePainting(_paint = Paint(paint))
            BrushTool.ERASER -> EraserPainting(_paint = Paint(paint))
        }
    }
}
