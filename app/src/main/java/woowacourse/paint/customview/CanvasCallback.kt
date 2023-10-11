package woowacourse.paint.customview

import woowacourse.paint.model.DrawingTool

interface CanvasCallback {
    fun onActionUp(drawingTool: DrawingTool)
    fun onUndoHistory()
    fun onRedoHistory()
    fun onClearHistory()
}
