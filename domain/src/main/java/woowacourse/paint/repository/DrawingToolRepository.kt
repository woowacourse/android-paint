package woowacourse.paint.repository

import woowacourse.paint.model.DrawingTool

interface DrawingToolRepository {
    fun getAllDrawingTools(): List<DrawingTool>
}
