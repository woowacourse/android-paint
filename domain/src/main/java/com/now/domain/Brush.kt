package com.now.domain

data class Brush(val brushColor: BrushColor, val brushWidth: BrushWidth) {
    fun changeColor(brushColor: BrushColor): Brush {
        return copy(brushColor = brushColor)
    }

    fun changeWidth(brushWidth: BrushWidth): Brush {
        return copy(brushWidth = brushWidth)
    }

    companion object {
        fun fromDefault(): Brush {
            return Brush(brushColor = BrushColor.RED, brushWidth = BrushWidth(5f))
        }
    }
}
