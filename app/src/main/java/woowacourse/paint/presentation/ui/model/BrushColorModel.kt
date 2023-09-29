package woowacourse.paint.presentation.ui.model

import android.graphics.Color
import androidx.annotation.ColorInt

enum class BrushColorModel(@ColorInt val value: Int) {
    RED(Color.RED),
    ORANGE(COLOR_ORANGE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
}

private const val COLOR_ORANGE = 0xFFFF7F00.toInt()
