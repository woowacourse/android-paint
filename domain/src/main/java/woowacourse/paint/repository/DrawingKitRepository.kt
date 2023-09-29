package woowacourse.paint.repository

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.model.PaintColor

interface DrawingKitRepository {

    fun getDrawingTool(): DrawingTool
    fun setDrawingTool(drawingTool: DrawingTool)
    fun getAllDrawingTools(): List<DrawingTool>
    fun getPaintColor(): PaintColor
    fun setPaintColor(paintColor: PaintColor)
    fun getAllPaintColors(): List<PaintColor>
}
