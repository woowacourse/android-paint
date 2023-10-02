package woowacourse.paint.view.model.mapper

import woowacourse.paint.domain.Lines
import woowacourse.paint.view.model.RichPaths
import woowacourse.paint.view.model.mapper.BrushMapper.toModel
import woowacourse.paint.view.model.mapper.PointsMapper.toModel

object LinesMapper {
    fun Lines.toModel(): RichPaths = value.map {
        it.points.toModel() to it.brush.toModel()
    }.let {
        RichPaths(it)
    }
}
