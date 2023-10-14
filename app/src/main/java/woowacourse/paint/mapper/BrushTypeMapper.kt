package woowacourse.paint.mapper

import com.now.domain.BrushType
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel

fun BrushType.toBrushTypeUiModel(): BrushTypeUiModel {
    return when (this) {
        BrushType.PEN -> { BrushTypeUiModel.PEN }
        BrushType.RECTANGLE -> { BrushTypeUiModel.RECTANGLE }
        BrushType.CIRCLE -> { BrushTypeUiModel.CIRCLE }
        BrushType.ERASER -> { BrushTypeUiModel.ERASER }
    }
}

fun BrushTypeUiModel.toBrushType(): BrushType {
    return when (this) {
        BrushTypeUiModel.PEN -> { BrushType.PEN }
        BrushTypeUiModel.RECTANGLE -> { BrushType.RECTANGLE }
        BrushTypeUiModel.CIRCLE -> { BrushType.CIRCLE }
        BrushTypeUiModel.ERASER -> { BrushType.ERASER }
    }
}
