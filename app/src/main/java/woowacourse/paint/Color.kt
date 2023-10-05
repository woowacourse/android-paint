package woowacourse.paint

import androidx.annotation.ColorRes

enum class Color(
    @ColorRes
    val resId: Int
) {
    Red(R.color.red),
    Orange(R.color.orange),
    Yellow(R.color.yellow),
    Green(R.color.green),
    Blue(R.color.blue)
}