package woowacourse.paint.action

sealed interface PaintBoardAction {
    data object ClearDrawings : PaintBoardAction
}
