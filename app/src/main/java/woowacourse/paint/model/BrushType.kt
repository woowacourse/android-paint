package woowacourse.paint.model

import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class BrushType(@DrawableRes val icon: Int) {
    PEN(R.drawable.pen),
    RECTANGLE(R.drawable.rectangle),
    CIRCLE(R.drawable.circle),
    ERASER(R.drawable.eraser),
}
