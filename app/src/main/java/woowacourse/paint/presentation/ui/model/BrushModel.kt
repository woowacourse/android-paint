package woowacourse.paint.presentation.ui.model

import woowacourse.paint.domain.model.BrushType

data class BrushModel(
    val color: BrushColorModel,
    val width: Float,
    val type: BrushType,
    val minWidth: Float,
    val maxWidth: Float,
)
