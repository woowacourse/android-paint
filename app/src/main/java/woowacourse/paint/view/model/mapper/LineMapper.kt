package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.Line
import woowacourse.paint.view.model.mapper.BrushMapper.toDomain
import woowacourse.paint.view.model.mapper.BrushMapper.toModel
import woowacourse.paint.view.model.mapper.PointsMapper.toDomain
import woowacourse.paint.view.model.mapper.PointsMapper.toModel
import woowacourse.paint.view.model.pen.Ink

object LineMapper {
    fun Ink.toDomain(): Line = Line(
        path.toDomain(), paint.toDomain()
    )

    fun Line.toModel(): Ink = Ink(
        brush.toModel(), points.toModel()
    )
}
