package woowacourse.paint.presentation.ui.model

import woowacourse.paint.domain.model.Brush
import woowacourse.paint.domain.model.BrushColor
import woowacourse.paint.domain.model.BrushWidth

fun Brush.toPresentation() = BrushModel(
    color = color.toPresentation(),
    width = width.value,
    type = type,
    minWidth = BrushWidth.MIN_WIDTH,
    maxWidth = BrushWidth.MAX_WIDTH,
)

fun BrushColor.toPresentation() = when (this) {
    BrushColor.RED -> BrushColorModel.RED
    BrushColor.ORANGE -> BrushColorModel.ORANGE
    BrushColor.YELLOW -> BrushColorModel.YELLOW
    BrushColor.GREEN -> BrushColorModel.GREEN
    BrushColor.BLUE -> BrushColorModel.BLUE
}

fun BrushColorModel.toBrushColor() = when (this) {
    BrushColorModel.RED -> BrushColor.RED
    BrushColorModel.ORANGE -> BrushColor.ORANGE
    BrushColorModel.YELLOW -> BrushColor.YELLOW
    BrushColorModel.GREEN -> BrushColor.GREEN
    BrushColorModel.BLUE -> BrushColor.BLUE
}
