package woowacourse.paint

import androidx.annotation.ColorRes

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
