package woowacourse.paint.util

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class Color(
    @ColorRes val colorRes: Int,
) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue), ;

    companion object {
        fun getColors(): List<Color> = entries
    }
}
