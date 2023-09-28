package woowacourse.paint.domain.model

data class Line(
    val color: LineColor,
    val width: LineWidth,
) {
    fun changeColor(color: LineColor): Line {
        return this.copy(color = color)
    }

    fun changeWidth(width: LineWidth): Line {
        return this.copy(width = width)
    }
}
