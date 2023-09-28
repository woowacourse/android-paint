package woowacourse.paint

import androidx.annotation.ColorRes

data class PaintColor(
    @ColorRes val color: Int,
    val isSelected: Boolean,
)
