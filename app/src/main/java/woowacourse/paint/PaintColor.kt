package woowacourse.paint

import androidx.annotation.ColorRes

enum class PaintColor(
    @ColorRes val colorRes: Int,
) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue), ;

    companion object {
        fun getAllPaintColors(): List<PaintColor> = entries
    }
}
