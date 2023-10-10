package woowacourse.paint.model

import woowacourse.paint.customView.content.BrushType

data class BrushTypeItem(
    val brushType: BrushType,
    val isSelected: Boolean,
) {
    companion object {
        fun getBrushTypeItems(selectedBrushType: BrushType): List<BrushTypeItem> =
            BrushType.values().map {
                if (it == selectedBrushType) return@map BrushTypeItem(it, true)
                BrushTypeItem(it, false)
            }
    }
}
