package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Painting {
    val paint: Paint
    fun movePath(x: Float, y: Float): Painting
    fun initPath(x: Float, y: Float)
    fun draw(canvas: Canvas)
    fun getNewPainting(): Painting

    fun from(brushTool: BrushTool): Painting {
        return when (brushTool) {
            BrushTool.PEN -> PenPainting(_paint = Paint(paint))
            BrushTool.RECTANGLE -> RectanglePainting(_paint = Paint(paint))
            BrushTool.CIRCLE -> CirclePainting(_paint = Paint(paint))
            BrushTool.ERASER -> EraserPainting(_paint = Paint(paint))
        }
    }
}
