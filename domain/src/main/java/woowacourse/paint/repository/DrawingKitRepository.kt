package woowacourse.paint.repository

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.model.PaintColor

interface DrawingKitRepository {

    fun getDrawingTool(): DrawingTool
    fun changeDrawingTool(drawingTool: DrawingTool)
    fun getAllDrawingTools(): List<DrawingTool>
    fun getThickness(): Float
    fun changeThickness(thickness: Float)
    fun getPaintColor(): PaintColor
    fun changePaintColor(paintColor: PaintColor)
    fun getAllPaintColors(): List<PaintColor>
}
