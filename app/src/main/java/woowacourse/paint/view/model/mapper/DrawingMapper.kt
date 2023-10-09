package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.Drawing
import woowacourse.paint.view.model.mapper.BrushMapper.toDomain
import woowacourse.paint.view.model.mapper.BrushMapper.toModel
import woowacourse.paint.view.model.mapper.PointsMapper.toDomain
import woowacourse.paint.view.model.mapper.PointsMapper.toModel
import woowacourse.paint.view.model.pen.ink.Ink

object DrawingMapper {
    fun Ink.toDomain(): Drawing = Drawing(
        path.toDomain(), paint.toDomain()
    )

    fun Drawing.toModel(): Ink = Ink(
        brush.toModel(), points.toModel()
    )
}
