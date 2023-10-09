package woowacourse.paint.view.model.mapper

import android.graphics.CornerPathEffect
import android.graphics.Paint
import woowacourse.paint.domain.Brush
import woowacourse.paint.domain.BrushColor
import woowacourse.paint.domain.BrushWidth
import woowacourse.paint.view.model.mapper.BrushColorMapper.toModel
import woowacourse.paint.view.model.mapper.BrushFillTypeMapper.toDomain
import woowacourse.paint.view.model.mapper.BrushFillTypeMapper.toModel

object BrushMapper {
    fun Brush.toModel(): Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = brushFillType.toModel()
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        pathEffect = CornerPathEffect(10F)
        color = brushColor.toModel()
        strokeWidth = brushWidth.value
    }

    fun Paint.toDomain(): Brush =
        Brush(BrushColor(color), BrushWidth(strokeWidth), style.toDomain())
}
