package woowacourse.paint.brush

import android.graphics.Color
import androidx.annotation.ColorInt

enum class ColorPalette(
    @ColorInt val colorRes: Int,
) {
    RED(Color.parseColor("#EA3323")),
    ORANGE(Color.parseColor("#F19D38")),
    YELLOW(Color.parseColor("#FCEC60")),
    GREEN(Color.parseColor("#67AD5B")),
    BLUE(Color.parseColor("#4350AF")),
}
