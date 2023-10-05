package woowacourse.paint.model

import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class BrushType(@DrawableRes val icon: Int) {
    PEN(R.drawable.pen),
    RECTANGLE(R.drawable.rectangle),
    FILLED_RECTANGLE(R.drawable.filled_rectangle),
    CIRCLE(R.drawable.circle),
    FILLED_CIRCLE(R.drawable.filled_circle),
    ERASER(R.drawable.eraser),
}
