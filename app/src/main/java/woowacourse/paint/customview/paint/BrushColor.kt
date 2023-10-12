package woowacourse.paint.customview.paint

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class BrushColor(@ColorRes val colorRes: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;
}
