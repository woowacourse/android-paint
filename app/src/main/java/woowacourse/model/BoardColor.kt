package woowacourse.model

import androidx.annotation.ColorInt
import woowacourse.paint.R

enum class BoardColor(@ColorInt val colorInt: Int) {
    RedColor(R.color.red),
    OrangeColor(R.color.orange),
    YellowColor(R.color.yellow),
    GreenColor(R.color.green),
    BlueColor(R.color.blue),
}
