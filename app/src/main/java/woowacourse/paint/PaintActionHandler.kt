package woowacourse.paint

import androidx.annotation.ColorRes

interface PaintActionHandler {
    fun changeColor(
        @ColorRes colorResId: Int,
    )
}
