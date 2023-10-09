package woowacourse.paint

sealed class PaintChangingState {
    object Nothing : PaintChangingState()
    object ColorChanging : PaintChangingState()
    object WidthChanging : PaintChangingState()
    object BrushChanging : PaintChangingState()
}
