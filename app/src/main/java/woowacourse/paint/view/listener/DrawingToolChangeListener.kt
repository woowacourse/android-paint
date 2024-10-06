package woowacourse.paint.view.listener

import woowacourse.paint.model.DrawingTool

interface DrawingToolChangeListener {
    fun onDrawingToolChanged(drawingTool: DrawingTool)
}
