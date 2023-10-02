package woowacourse.paint.view.model.mapper

import android.graphics.Path
import androidx.core.graphics.flatten
import woowacourse.paint.domain.Point
import woowacourse.paint.domain.Points

object PointsMapper {
    fun Points.toModel(): Path = Path().apply {
        moveTo(value.first().x, value.first().y)
        value.map {
            lineTo(it.x, it.y)
        }
    }

    fun Path.toDomain(): Points = Points(
        flatten().flatMapIndexed { index, pathSegment ->
            if (index == 0) listOf(
                Point(pathSegment.start.x, pathSegment.start.y),
                Point(pathSegment.end.x, pathSegment.end.y)
            )
            else listOf(Point(pathSegment.end.x, pathSegment.end.y))
        }
    )
}
