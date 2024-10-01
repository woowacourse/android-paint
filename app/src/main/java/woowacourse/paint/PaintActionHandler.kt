package woowacourse.paint

import androidx.annotation.ColorRes

interface PaintActionHandler {
    fun changeColorRes(@ColorRes colorRes: Int)
}
