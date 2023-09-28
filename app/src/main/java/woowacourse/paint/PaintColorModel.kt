package woowacourse.paint

import androidx.annotation.ColorRes

data class PaintColorModel(
    @ColorRes val color: Int,
    val isSelected: Boolean,
)
