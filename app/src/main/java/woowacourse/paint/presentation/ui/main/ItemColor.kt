package woowacourse.paint.presentation.ui.main

import woowacourse.paint.presentation.ui.model.BrushColorModel

data class ItemColor(
    val color: BrushColorModel,
    val onColorClick: (BrushColorModel) -> Unit,
)
