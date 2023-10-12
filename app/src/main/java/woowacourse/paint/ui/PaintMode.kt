package woowacourse.paint.ui

import woowacourse.paint.R

enum class PaintMode(val drawable: Int) {
    PEN(R.drawable.pen), RECTANGLE(R.drawable.rectangle), FILL_RECTANGLE(R.drawable.fill_rectangle), CIRCLE(
        R.drawable.circle
    ),
    FILL_CIRCLE(R.drawable.fill_circle), ERASER(R.drawable.eraser)
}
