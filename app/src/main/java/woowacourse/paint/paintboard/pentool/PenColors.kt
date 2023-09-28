package woowacourse.paint.paintboard.pentool

import androidx.annotation.ColorRes
import woowacourse.paint.R

class PenColors {
    private val colors = listOf<Int>(
        R.color.black,
        R.color.darkBlue,
        R.color.blue,
        R.color.skyBlue,
        R.color.green,
        R.color.lightGreen,
        R.color.red,
        R.color.orange,
        R.color.yellow,
    )
    private val value
        get() = colors.map { PenColor(it) }.toMutableList()

    @ColorRes
    private var currentPosition: Int = 0
    val currentPenColor: Int get() = colors[currentPosition]

    fun selectColor(position: Int): List<PenColor> {
        currentPosition = position
        return value.apply {
            this[position] = this[position].copy(isSelected = true)
        }
    }

    companion object {
        val DEFAULT_COLOR_POSITION = 0
    }
}
