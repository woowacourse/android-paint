package woowacourse.paint.ui.mapper

import woowacourse.paint.R
import woowacourse.paint.ui.PaintMode

fun PaintMode.toUi(): Int {
    return when (this) {
        PaintMode.PEN -> R.drawable.pen
        PaintMode.RECTANGLE -> R.drawable.rectangle
        PaintMode.FILL_RECTANGLE -> R.drawable.fill_rectangle
        PaintMode.CIRCLE -> R.drawable.circle
        PaintMode.FILL_CIRCLE -> R.drawable.fill_circle
        PaintMode.ERASER -> R.drawable.eraser
    }
}
