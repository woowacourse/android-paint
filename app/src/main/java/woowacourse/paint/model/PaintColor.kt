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
        val DEFAULT_COLOR: PaintColor
            get() = values().first()

        fun getColor(index: Int): PaintColor {
            return values()[index]
        }
    }
}
