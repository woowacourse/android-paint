package woowacourse.paint.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

data class PaintColor(
    @ColorRes val colorRes: Int,
    val isSelected: Boolean,
) {
    companion object {
        val DEFAULT_COLOR = PaintColor(R.color.red, true)
    }
}
