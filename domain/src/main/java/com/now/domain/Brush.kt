package com.now.domain

data class Brush(
    val brushType: BrushType,
    val brushColor: BrushColor,
    val brushWidth: BrushWidth,
) {
    fun changeType(type: BrushType): Brush {
        return copy(brushType = type)
    }

    fun changeColor(color: BrushColor): Brush {
        return copy(brushColor = color)
    }

    fun changeWidth(width: BrushWidth): Brush {
        return copy(brushWidth = width)
    }

    companion object {
        fun fromDefault(): Brush {
            return Brush(
                brushType = BrushType.PEN,
                brushColor = BrushColor.RED,
                brushWidth = BrushWidth(5f),
            )
        }
    }
}
