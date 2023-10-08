package woowacourse.paint.util

import android.graphics.Canvas
import android.graphics.Paint
import woowacourse.paint.model.Circle

fun Canvas.drawCircle(circle: Circle, paint: Paint) {
    drawCircle(circle.centerX, circle.centerY, circle.radius, paint)
}
