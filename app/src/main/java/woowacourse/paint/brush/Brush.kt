package woowacourse.paint.brush

data class Brush(
    val colorPalette: ColorPalette = INIT_COLOR,
    val width: Float = DEFAULT_BRUSH_WIDTH,
) {
    fun changeColor(colorPalette: ColorPalette): Brush {
        return this.copy(colorPalette = colorPalette)
    }

    fun changeWidth(width: Float): Brush {
        return this.copy(width = width)
    }

    companion object {
        val INIT_COLOR = ColorPalette.RED
        private const val DEFAULT_BRUSH_WIDTH = 10f
    }
}
