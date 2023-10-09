package woowacourse.paint.domain.model

data class Brush(
    val color: BrushColor,
    val width: BrushWidth,
    val type: BrushType,
) {
    fun changeColor(color: BrushColor): Brush {
        return this.copy(color = color)
    }

    fun changeWidth(width: BrushWidth): Brush {
        return this.copy(width = width)
    }

    fun changeType(type: BrushType): Brush {
        return this.copy(type = type)
    }
}
