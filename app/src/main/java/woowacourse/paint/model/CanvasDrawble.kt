package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface CanvasDrawble {
    fun movePath(x: Float, y: Float)
    fun initPath(x: Float, y: Float)
    fun draw(canvas: Canvas, paint: Paint)

    fun newPainting(): CanvasDrawble

    fun from(brushTool: BrushTool): CanvasDrawble {
        return when (brushTool) {
            BrushTool.PEN -> PenDrawble()
            BrushTool.RECTANGLE -> RectangleDrawble()
            BrushTool.CIRCLE -> CircleDrawble()
            BrushTool.ERASER -> EraserDrawble()
        }
    }
}
