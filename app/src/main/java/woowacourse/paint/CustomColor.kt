package woowacourse.paint

import android.graphics.Color

enum class CustomColor(val colorCode: Int) {
    RED(Color.RED), ORANGE(Color.parseColor("#FF9802")), YELLOW(Color.YELLOW), GREEN(Color.GREEN), BLUE(Color.BLUE);

    companion object {
        fun getAllColors() = listOf(RED, ORANGE, YELLOW, GREEN, BLUE)
    }
}
