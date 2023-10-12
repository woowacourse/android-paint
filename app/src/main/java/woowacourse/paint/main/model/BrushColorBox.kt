package woowacourse.paint.main.model

import woowacourse.paint.customview.paint.BrushColor

data class BrushColorBox(
    val brushColor: BrushColor,
    val isSelected: Boolean = false,
) {
    companion object {
        fun getColorBoxes(selectedColor: BrushColor): List<BrushColorBox> =
            BrushColor.values().map { brushColor ->
                if (selectedColor == brushColor) {
                    BrushColorBox(brushColor, true)
                } else {
                    BrushColorBox(brushColor, false)
                }
            }
    }
}
