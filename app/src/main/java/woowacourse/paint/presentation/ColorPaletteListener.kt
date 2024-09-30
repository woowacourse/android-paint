package woowacourse.paint.presentation

import androidx.annotation.ColorRes

interface ColorPaletteListener {
    fun onSelectColor(@ColorRes colorResId: Int)
}
