package woowacourse.paint.model

import androidx.annotation.StringRes
import woowacourse.paint.R

enum class Tools(@StringRes val stringRes: Int) {
    LINE(R.string.item_pen),
    RECTANGLE(R.string.item_rectangle),
    CIRCLE(R.string.item_circle),
    ERASER(R.string.item_eraser),
}
