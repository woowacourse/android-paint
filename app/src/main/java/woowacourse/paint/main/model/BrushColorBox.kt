package woowacourse.paint.main.model

import woowacourse.paint.customview.BrushColor

data class BrushColorBox(
    val brushColor: BrushColor,
    val isSelected: Boolean = false,
)
