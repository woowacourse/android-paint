package woowacourse.paint.utils

import woowacourse.paint.R
import woowacourse.paint.canvas.Tool
import woowacourse.paint.model.ToolUiModel

fun Tool.toUiModel(): ToolUiModel {
    return when (this) {
        Tool.PEN -> ToolUiModel(R.string.pen)
        Tool.RECTANGLE -> ToolUiModel(R.string.rectangle)
        Tool.CIRCLE -> ToolUiModel(R.string.circle)
        Tool.ERASER -> ToolUiModel(R.string.eraser)
    }
}
