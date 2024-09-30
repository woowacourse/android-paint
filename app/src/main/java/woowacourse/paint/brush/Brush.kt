package woowacourse.paint.brush

data class Brush(
    val colorPalette: ColorPalette = ColorPalette.RED,
    val width: Float = DEFAULT_BRUSH_WIDTH,
) {
    fun changeColor(colorPalette: ColorPalette): Brush {
        return this.copy(colorPalette = colorPalette)
    }

    fun changeWidth(width: Float): Brush {
        return this.copy(width = width)
    }

    companion object {
        private const val DEFAULT_BRUSH_WIDTH = 10f
    }
}
