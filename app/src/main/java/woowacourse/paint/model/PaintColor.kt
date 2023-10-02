package woowacourse.paint.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class PaintColor(@ColorRes val colorRes: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;

    companion object {
        fun getDefaultColor(): PaintColor {
            return RED
        }

        fun getColor(index: Int): PaintColor {
            return values()[index]
        }
    }
}
