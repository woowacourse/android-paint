package woowacourse.paint.model

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class ColorPalette(
    @ColorRes val colorResId: Int,
) {
    RED(colorResId = R.color.red),
    ORANGE(colorResId = R.color.orange),
    YELLOW(colorResId = R.color.yellow),
    GREEN(colorResId = R.color.green),
    BLUE(colorResId = R.color.blue), ;

    @ColorInt
    fun getColor(context: Context): Int {
        return context.getColor(colorResId)
    }
}
