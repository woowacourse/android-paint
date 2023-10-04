package woowacourse.paint.model

import androidx.annotation.ColorRes

data class ColorBox(
    @ColorRes val color: Int,
    val isSelected: Boolean = false,
)
