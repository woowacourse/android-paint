package woowacourse.paint.repository

import woowacourse.paint.model.DrawingTool

interface DrawingToolRepository {

    fun getDrawingTool(): DrawingTool
    fun getAllDrawingTools(): List<DrawingTool>
}
