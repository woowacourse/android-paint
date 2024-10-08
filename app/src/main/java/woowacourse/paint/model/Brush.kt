package woowacourse.paint.model

data class Brush(
    val strokeWidth: Float = DEFAULT_STROKE_WIDTH,
    val color: Int = DEFAULT_COLOR,
    val brushType: BrushType = DEFAULT_BRUSH_TYPE,
) {
    fun update(updateBrush: Brush.() -> Brush): Brush {
        return this.updateBrush()
    }

    fun changeWidth(width: Float): Brush {
        return copy(strokeWidth = width)
    }

    fun changeColor(color: Int): Brush {
        return copy(color = color)
    }

    fun changeBrushType(brushType: BrushType): Brush {
        return copy(brushType = brushType)
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 10f
        const val DEFAULT_COLOR = 0xFF000000.toInt()
        val DEFAULT_BRUSH_TYPE = BrushType.PENCIL
    }
}
