package woowacourse.model

import androidx.annotation.ColorInt

enum class BoardColor(@ColorInt val colorInt: Int) {
    RedColor(0xFF0000),
    OrangeColor(0xFF9800),
    YellowColor(0xFFEB3B),
    GreenColor(0x4CAF50),
    BlueColor(0x4050B5),
}
