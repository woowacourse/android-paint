package woowacourse.paint.presentation.ui.model

import woowacourse.paint.domain.model.LineColor
import woowacourse.paint.domain.model.LineCondition
import woowacourse.paint.domain.model.LineWidth

fun LineCondition.toPresentation() =
    LineModel(color.toPresentation(), width.value, LineWidth.MIN_WIDTH, LineWidth.MAX_WIDTH)

fun LineColor.toPresentation() = when (this) {
    LineColor.RED -> LineColorModel.RED
    LineColor.ORANGE -> LineColorModel.ORANGE
    LineColor.YELLOW -> LineColorModel.YELLOW
    LineColor.GREEN -> LineColorModel.GREEN
    LineColor.BLUE -> LineColorModel.BLUE
}

fun LineColorModel.toLineColor() = when (this) {
    LineColorModel.RED -> LineColor.RED
    LineColorModel.ORANGE -> LineColor.ORANGE
    LineColorModel.YELLOW -> LineColor.YELLOW
    LineColorModel.GREEN -> LineColor.GREEN
    LineColorModel.BLUE -> LineColor.BLUE
}
