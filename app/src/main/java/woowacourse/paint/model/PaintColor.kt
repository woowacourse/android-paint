package woowacourse.paint.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class PaintColor(@ColorRes val color: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;

    companion object {
        fun getColorBoxes(@ColorRes color: Int): List<ColorBox> {
            return PaintColor.values().map {
                ColorBox(it.color, it.color == color)
            }
        }
    }
}
