package woowacourse.paint

import androidx.annotation.ColorInt

data class ColorUiModel(
    val id: Int,
    @ColorInt val color: Int,
)
