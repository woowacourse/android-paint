package woowacourse.paint.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class PaletteColor(@ColorRes val color: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
}
