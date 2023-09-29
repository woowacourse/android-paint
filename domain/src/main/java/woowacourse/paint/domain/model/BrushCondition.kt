package woowacourse.paint.domain.model

data class BrushCondition(
    val color: BrushColor,
    val width: BrushWidth,
) {
    fun changeColor(color: BrushColor): BrushCondition {
        return this.copy(color = color)
    }

    fun changeWidth(width: BrushWidth): BrushCondition {
        return this.copy(width = width)
    }
}
