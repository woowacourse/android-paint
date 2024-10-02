package woowacourse.paint.presentation.palette

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class ColorUiModel(@ColorRes val resId: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
}
