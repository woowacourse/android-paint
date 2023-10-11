package woowacourse.paint.presentation.uimodel

import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class BrushTypeUiModel(@DrawableRes val description: Int) : BrushToolView {
    PEN(R.drawable.ic_brush),
    RECTANGLE(R.drawable.ic_rectangle),
    CIRCLE(R.drawable.ic_circle),
    ERASER(R.drawable.ic_eraser),
}
