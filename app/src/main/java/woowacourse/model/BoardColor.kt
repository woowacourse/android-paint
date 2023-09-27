package woowacourse.model

import androidx.annotation.ColorRes
import woowacourse.paint.R

enum class BoardColor(@ColorRes val colorInt: Int) {
    RedColor(R.color.red),
    OrangeColor(R.color.orange),
    YellowColor(R.color.yellow),
    GreenColor(R.color.green),
    BlueColor(R.color.blue),
}
