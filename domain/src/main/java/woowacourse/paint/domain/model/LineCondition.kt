package woowacourse.paint.domain.model

data class LineCondition(
    val color: LineColor,
    val width: LineWidth,
) {
    fun changeColor(color: LineColor): LineCondition {
        return this.copy(color = color)
    }

    fun changeWidth(width: LineWidth): LineCondition {
        return this.copy(width = width)
    }
}
