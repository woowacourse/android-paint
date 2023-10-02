package woowacourse.paint.ui.model.mapper

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.ui.model.DrawingToolModel

fun DrawingTool.toDrawingToolModel(): DrawingToolModel {
    return when (this) {
        DrawingTool.PEN -> DrawingToolModel.PEN
        DrawingTool.HIGHLIGHTER -> DrawingToolModel.HIGHLIGHTER
        DrawingTool.ERASER -> DrawingToolModel.ERASER
    }
}

fun DrawingToolModel.toDrawingTool(): DrawingTool {
    return when (this) {
        DrawingToolModel.PEN -> DrawingTool.PEN
        DrawingToolModel.HIGHLIGHTER -> DrawingTool.HIGHLIGHTER
        DrawingToolModel.ERASER -> DrawingTool.ERASER
    }
}
