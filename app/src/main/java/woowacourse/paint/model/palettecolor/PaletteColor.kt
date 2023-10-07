package woowacourse.paint.model.palettecolor

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class PaletteColor(@ColorRes val resourceId: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BlUE(R.color.blue),
    ;

    companion object {
        fun getAll(): List<PaletteColor> {
            return PaletteColor.values().toList()
        }
    }
}
