package woowacourse.paint.mapper

import com.now.domain.BrushColor
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

fun BrushColor.toBrushColorUiModel(): BrushColorUiModel {
    return when (this) {
        BrushColor.RED -> BrushColorUiModel.RED
        BrushColor.YELLOW -> BrushColorUiModel.YELLOW
        BrushColor.ORANGE -> BrushColorUiModel.ORANGE
        BrushColor.GREEN -> BrushColorUiModel.GREEN
        BrushColor.BLUE -> BrushColorUiModel.BLUE
    }
}

fun BrushColorUiModel.toBrushColor(): BrushColor {
    return when (this) {
        BrushColorUiModel.RED -> BrushColor.RED
        BrushColorUiModel.YELLOW -> BrushColor.YELLOW
        BrushColorUiModel.ORANGE -> BrushColor.ORANGE
        BrushColorUiModel.GREEN -> BrushColor.GREEN
        BrushColorUiModel.BLUE -> BrushColor.BLUE
    }
}
