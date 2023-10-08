package woowacourse.paint.main.model

import woowacourse.paint.customview.paint.BrushColor

data class BrushColorBox(
    val brushColor: BrushColor,
    val isSelected: Boolean = false,
)
