package woowacourse.paint

import android.content.Context
import androidx.core.content.ContextCompat

enum class PaletteColor(private val hexColor: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BlUE(R.color.blue),
    ;

    fun convertToArgb(context: Context): Int {
        return ContextCompat.getColor(context, hexColor)
    }
}
