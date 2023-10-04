package woowacourse.paint.mapper

import com.now.domain.Brush
import com.now.domain.BrushColor
import com.now.domain.BrushWidth
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushUiModel

fun Brush.toBrushUiModel(): BrushUiModel {
    return when (this.brushColor) {
        BrushColor.RED -> { BrushUiModel(BrushColorUiModel.RED, this.brushWidth.width) }
        BrushColor.YELLOW -> { BrushUiModel(BrushColorUiModel.YELLOW, this.brushWidth.width) }
        BrushColor.ORANGE -> { BrushUiModel(BrushColorUiModel.ORANGE, this.brushWidth.width) }
        BrushColor.GREEN -> { BrushUiModel(BrushColorUiModel.GREEN, this.brushWidth.width) }
        BrushColor.BLUE -> { BrushUiModel(BrushColorUiModel.BLUE, this.brushWidth.width) }
    }
}

fun BrushUiModel.toBrush(): Brush {
    return when (this.brushColor) {
        BrushColorUiModel.RED -> { Brush(BrushColor.RED, BrushWidth(this.brushWidth)) }
        BrushColorUiModel.YELLOW -> { Brush(BrushColor.YELLOW, BrushWidth(this.brushWidth)) }
        BrushColorUiModel.ORANGE -> { Brush(BrushColor.ORANGE, BrushWidth(this.brushWidth)) }
        BrushColorUiModel.GREEN -> { Brush(BrushColor.GREEN, BrushWidth(this.brushWidth)) }
        BrushColorUiModel.BLUE -> { Brush(BrushColor.BLUE, BrushWidth(this.brushWidth)) }
    }
}
