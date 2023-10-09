package woowacourse.paint.presentation.ui.main.brushTypes

import woowacourse.paint.domain.model.BrushType

data class ItemBrushType(
    val brushType: BrushType,
    val onBrushTypeClick: (BrushType) -> Unit,
)
