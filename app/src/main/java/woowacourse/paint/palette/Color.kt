package woowacourse.paint.palette

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class Color(@ColorRes val resId: Int) {
    BLACK(R.color.black), RED(R.color.red), ORANGE(R.color.orange), YELLOW(R.color.yellow), GREEN(R.color.green), BLUE(
        R.color.blue,
    )
}
