package woowacourse.paint

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

object Palette : ColorConvertible {
    override fun convertAllColor(context: Context): List<Int> {
        return PaletteColor.values().map {
            it.color.convertToArgb(context)
        }
    }

    @ColorInt
    private fun Int.convertToArgb(context: Context): Int {
        return ContextCompat.getColor(context, this)
    }
}
