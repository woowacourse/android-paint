package woowacourse.paint.model

data class BrushBox(
    val brushTool: PaintBrush,
    val isSelected: Boolean = false,
)
