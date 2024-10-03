package woowacourse.paint.ui

import androidx.annotation.ColorRes

interface PaletteAction {
    fun onColorSelected(
        @ColorRes color: Int,
    )
}
