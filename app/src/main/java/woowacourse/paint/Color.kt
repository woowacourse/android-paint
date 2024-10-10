package woowacourse.paint

import androidx.annotation.ColorRes

enum class Color(
    @ColorRes val colorId: Int,
) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;

    companion object {
        const val DEFAULT_DRAWING_COLOR = android.graphics.Color.RED
    }
}
