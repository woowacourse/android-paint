package woowacourse.paint.presentation.uimodel

import android.graphics.Paint
import com.now.domain.BrushWidth

data class BrushUiModel(
    val brushType: BrushTypeUiModel,
    val brushColor: BrushColorUiModel,
    val brushWidth: BrushWidth,
) {
    fun changeType(type: BrushTypeUiModel): BrushUiModel {
        return copy(brushType = type)
    }

    fun changeColor(color: BrushColorUiModel): BrushUiModel {
        return copy(brushColor = color)
    }

    fun changeWidth(width: BrushWidth): BrushUiModel {
        return copy(brushWidth = width)
    }

    fun fromPaint(): Paint {
        return Paint().apply {
            strokeJoin = Paint.Join.ROUND
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = brushWidth.width
            color = brushColor.color
        }
    }

    companion object {
        fun fromDefault(): BrushUiModel {
            return BrushUiModel(
                brushType = BrushTypeUiModel.PEN,
                brushColor = BrushColorUiModel.RED,
                brushWidth = BrushWidth(5f),
            )
        }
    }
}
