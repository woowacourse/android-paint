package woowacourse.paint.data

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.repository.DrawingToolRepository

class DefaultDrawingToolRepository : DrawingToolRepository {
    private var drawingTool = DrawingTool.PEN
    private val drawingTools = DrawingTool.values().toList()

    override fun getDrawingTool(): DrawingTool {
        return drawingTools.first()
    }

    override fun setDrawingTool(drawingTool: DrawingTool) {
        this.drawingTool = drawingTool
    }

    override fun getAllDrawingTools(): List<DrawingTool> {
        return drawingTools
    }
}
