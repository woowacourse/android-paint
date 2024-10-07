package woowacourse.paint.brush

import woowacourse.paint.R

enum class BrushType(
    val brushNameId: Int,
) {
    PEN(R.string.brush_pen),
    RECTANGLE(R.string.brush_rectangle),
    CIRCLE(R.string.brush_circle),
    ERASER(R.string.brush_eraser),
}
