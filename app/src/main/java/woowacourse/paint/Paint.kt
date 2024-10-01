package woowacourse.paint

import androidx.annotation.ColorInt

data class Paint(@ColorInt val color: Int) {

    companion object {
        val dummy = listOf(
            Paint(0xFF000000.toInt()),
            Paint(0xFF0000FF.toInt()),
            Paint(0xFF00FF00.toInt()),
            Paint(0xFFFF0000.toInt()),
            Paint(0xFFFFFF00.toInt()),
            Paint(0xFFFF00FF.toInt()),
            Paint(0xFF00FFFF.toInt()),
            Paint(0xFFFFFFFF.toInt()),
        )
    }
}