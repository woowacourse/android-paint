package woowacourse.paint.utils

import woowacourse.paint.R
import woowacourse.paint.canvas.DrawingTool
import woowacourse.paint.model.ToolUiModel

fun DrawingTool.toUiModel(): ToolUiModel {
    return when (this) {
        DrawingTool.PEN -> ToolUiModel(R.string.pen)
        DrawingTool.RECTANGLE -> ToolUiModel(R.string.rectangle)
        DrawingTool.CIRCLE -> ToolUiModel(R.string.circle)
        DrawingTool.ERASER -> ToolUiModel(R.string.eraser)
    }
}
