package woowacourse.paint.model

data class BrushColorItem(
    val color: BrushColor,
    val isSelected: Boolean,
) {
    companion object {
        fun getBoardColorItems(selectedColor: BrushColor): List<BrushColorItem> =
            BrushColor.values().map {
                if (it == selectedColor) return@map BrushColorItem(it, true)
                BrushColorItem(it, false)
            }
    }
}
