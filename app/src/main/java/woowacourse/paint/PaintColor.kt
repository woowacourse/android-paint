package woowacourse.paint

import androidx.annotation.ColorRes

data class PaintColor(
    val name: String,
    @ColorRes val res: Int,
)
