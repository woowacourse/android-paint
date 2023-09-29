package woowacourse.paint.data

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.repository.DrawingToolRepository

class DefaultDrawingToolRepository : DrawingToolRepository {
    private val drawingTools = DrawingTool.values().toList()

    override fun getAllDrawingTools(): List<DrawingTool> {
        return drawingTools
    }
}
