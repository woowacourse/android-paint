package woowacourse.paint.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

data class PaintColor(
    @ColorRes val colorRes: Int,
    val isSelected: Boolean,
) {
    companion object {
        val colors = listOf(
            PaintColor(R.color.red, true),
            PaintColor(R.color.orange, false),
            PaintColor(R.color.yellow, false),
            PaintColor(R.color.green, false),
            PaintColor(R.color.blue, false),
        )
    }
}
