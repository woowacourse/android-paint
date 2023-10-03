package woowacourse.paint

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

enum class PaletteColor(@ColorRes val color: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BlUE(R.color.blue),
    ;

    @ColorInt
    private fun convertToArgb(context: Context): Int {
        return ContextCompat.getColor(context, color)
    }

    companion object {
        fun getAllArgb(context: Context): List<Int> {
            return PaletteColor.values().map {
                it.convertToArgb(context)
            }
        }
    }
}
