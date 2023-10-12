package woowacourse.paint.customview

import woowacourse.paint.model.Painting

interface CanvasCallback {
    fun onActionUp(drawingTool: Painting)
    fun onUndoHistory()
    fun onRedoHistory()
    fun onClearHistory()
}
