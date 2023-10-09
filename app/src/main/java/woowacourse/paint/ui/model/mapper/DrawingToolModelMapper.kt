package woowacourse.paint.ui.model.mapper

import woowacourse.paint.model.DrawingTool
import woowacourse.paint.ui.model.DrawingToolModel

fun DrawingTool.toDrawingToolModel(): DrawingToolModel {
    return when (this) {
        DrawingTool.PEN -> DrawingToolModel.PEN
        DrawingTool.HIGHLIGHTER -> DrawingToolModel.HIGHLIGHTER
        DrawingTool.CIRCLE -> DrawingToolModel.CIRCLE
        DrawingTool.RECTANGLE -> DrawingToolModel.RECTANGLE
        DrawingTool.ERASER -> DrawingToolModel.ERASER
    }
}

fun DrawingToolModel.toDrawingTool(): DrawingTool {
    return when (this) {
        DrawingToolModel.PEN -> DrawingTool.PEN
        DrawingToolModel.HIGHLIGHTER -> DrawingTool.HIGHLIGHTER
        DrawingToolModel.CIRCLE -> DrawingTool.CIRCLE
        DrawingToolModel.RECTANGLE -> DrawingTool.RECTANGLE
        DrawingToolModel.ERASER -> DrawingTool.ERASER
    }
}
