package woowacourse.paint.util

import android.graphics.Path
import woowacourse.paint.model.Circle

fun Path.addCircle(circle: Circle, direction: Path.Direction) {
    addCircle(circle.centerX, circle.centerY, circle.radius, direction)
}
