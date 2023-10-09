package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt

interface Painting {
    val paint: Paint
    fun movePath(x: Float, y: Float): Painting
    fun initPath(prevX: Float, prevY: Float, x: Float, y: Float)
    fun draw(canvas: Canvas)
    fun setStroke(value: Float): Painting
    fun setColor(@ColorInt color: Int): Painting

    fun getNewPainting(): Painting

    fun setPaintBrush(brushTool: BrushTool): Painting {
        return when (brushTool) {
            BrushTool.PEN -> PenPainting(_paint = Paint(paint))
            BrushTool.RECTANGLE -> RectanglePainting(_paint = Paint(paint))
            BrushTool.CIRCLE -> CirclePainting(_paint = Paint(paint))
            BrushTool.ERASER -> EraserPainting(_paint = Paint(paint))
        }
    }
}
