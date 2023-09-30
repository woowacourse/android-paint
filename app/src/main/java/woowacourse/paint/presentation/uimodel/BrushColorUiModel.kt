package woowacourse.paint.presentation.uimodel

import androidx.annotation.ColorInt

enum class BrushColorUiModel(@ColorInt val color: Int) {
    RED(0xFFEA3323.toInt()),
    YELLOW(0xFFFCEC60.toInt()),
    ORANGE(0xFFF19D38.toInt()),
    GREEN(0xFF67AD5B.toInt()),
    BLUE(0xFF4350AF.toInt()),
}
