package woowacourse.paint

import androidx.annotation.ColorRes

enum class ColorPalette(@ColorRes val color: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
}
