package woowacourse.paint.model

data class Brush(
    val strokeWidth: Float = DEFAULT_STROKE_WIDTH,
    val color: Int = DEFAULT_COLOR,
) {
    fun changeWidth(width: Float): Brush {
        return copy(strokeWidth = width)
    }

    fun changeColor(color: Int): Brush {
        return copy(color = color)
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 10f
        const val DEFAULT_COLOR = 0xFF000000.toInt()
    }
}
