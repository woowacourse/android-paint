package woowacourse.paint.action

import androidx.annotation.ColorRes

interface PaintActionHandler {
    fun changeColorRes(
        @ColorRes colorRes: Int,
    )
}
