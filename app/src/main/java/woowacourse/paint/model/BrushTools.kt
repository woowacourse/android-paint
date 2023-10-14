package woowacourse.paint.model

import woowacourse.paint.R

enum class BrushTools(val drawable: Int) {
    PEN(R.drawable.pen),
    RECTANGLE(R.drawable.rectangle),
    FILL_RECTANGLE(R.drawable.fill_rectangle),
    CIRCLE(R.drawable.circle),
    FILL_CIRCLE(R.drawable.fill_circle),
    ERASER(R.drawable.eraser),
}
