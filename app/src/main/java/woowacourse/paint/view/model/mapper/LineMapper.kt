package woowacourse.paint.view.model.mapper

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.domain.Line
import woowacourse.paint.view.model.mapper.BrushMapper.toDomain
import woowacourse.paint.view.model.mapper.PointsMapper.toDomain

object LineMapper {
    fun toLine(path: Path, paint: Paint): Line {
        return Line(
            path.toDomain(), paint.toDomain()
        )
    }
}
