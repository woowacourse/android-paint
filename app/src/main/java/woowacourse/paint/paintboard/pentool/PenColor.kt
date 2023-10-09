package woowacourse.paint.paintboard.pentool

import androidx.annotation.ColorRes

data class PenColor(
    @ColorRes val color: Int,
    val isSelected: Boolean = false,
)
