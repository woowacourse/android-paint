package woowacourse.paint.brush

data class Brush(
    val brushType: BrushType = INIT_BRUSH_TYPE,
    val colorPalette: ColorPalette = INIT_COLOR,
    val width: Float = DEFAULT_BRUSH_WIDTH,
) {
    fun changeType(type: BrushType): Brush {
        return this.copy(brushType = type)
    }

    fun changeColor(colorPalette: ColorPalette): Brush {
        return this.copy(colorPalette = colorPalette)
    }

    fun changeWidth(width: Float): Brush {
        return this.copy(width = width)
    }

    companion object {
        val INIT_COLOR = ColorPalette.RED
        val INIT_BRUSH_TYPE = BrushType.PEN
        private const val DEFAULT_BRUSH_WIDTH = 10f
    }
}
