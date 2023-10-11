package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Painting {
    fun movePath(x: Float, y: Float)
    fun initPath(x: Float, y: Float)
    fun draw(canvas: Canvas, paint: Paint)

    fun newPainting(): Painting

    fun from(brushTool: BrushTool): Painting {
        return when (brushTool) {
            BrushTool.PEN -> PenPainting()
            BrushTool.RECTANGLE -> RectanglePainting()
            BrushTool.CIRCLE -> CirclePainting()
            BrushTool.ERASER -> EraserPainting()
        }
    }
}
