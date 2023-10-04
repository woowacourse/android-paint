package woowacourse.paint.view.model.mapper

import android.graphics.Paint
import woowacourse.paint.domain.BrushFillType

object BrushFillTypeMapper {
    fun BrushFillType.toModel(): Paint.Style {
        return when (this) {
            BrushFillType.FILL -> Paint.Style.FILL
            BrushFillType.STROKE -> Paint.Style.STROKE
        }
    }

    fun Paint.Style.toDomain(): BrushFillType {
        return when (this) {
            Paint.Style.FILL -> BrushFillType.FILL
            Paint.Style.STROKE -> BrushFillType.STROKE
            Paint.Style.FILL_AND_STROKE -> BrushFillType.FILL
        }
    }
}
