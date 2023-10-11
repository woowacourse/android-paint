package woowacourse.paint.utils

import woowacourse.paint.R
import woowacourse.paint.canvas.DrawingTool
import woowacourse.paint.model.DrawingToolUiModel

fun DrawingTool.toUiModel(): DrawingToolUiModel {
    return when (this) {
        DrawingTool.PEN -> DrawingToolUiModel(R.string.pen)
        DrawingTool.RECTANGLE -> DrawingToolUiModel(R.string.rectangle)
        DrawingTool.CIRCLE -> DrawingToolUiModel(R.string.circle)
        DrawingTool.ERASER -> DrawingToolUiModel(R.string.eraser)
    }
}
