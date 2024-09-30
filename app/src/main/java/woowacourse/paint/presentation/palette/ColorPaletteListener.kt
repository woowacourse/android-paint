package woowacourse.paint.presentation.palette

import androidx.annotation.ColorRes

interface ColorPaletteListener {
    fun onSelectColor(
        @ColorRes colorResId: Int,
    )
}
