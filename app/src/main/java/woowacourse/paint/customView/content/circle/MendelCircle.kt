package woowacourse.paint.customView.content.circle

import android.graphics.PointF
import kotlin.math.abs
import kotlin.math.min

class MendelCircle(private val startPoint: PointF) {
    var endPoint: PointF = PointF(startPoint.x, startPoint.y)
        private set
    val radius: Float
        get() = min(abs(startPoint.x - endPoint.x), abs(startPoint.y - endPoint.y)) / 2

    val centerPoint: PointF
        get() = PointF(
            if (startPoint.x > endPoint.x) {
                startPoint.x - radius
            } else {
                startPoint.x + radius
            },
            if (startPoint.y > endPoint.y) {
                startPoint.y - radius
            } else {
                startPoint.y + radius
            },
        )

    fun deepCopy(): MendelCircle {
        return MendelCircle(PointF(startPoint.x, startPoint.y)).apply {
            this@apply.endPoint = PointF(this@MendelCircle.endPoint.x, this@MendelCircle.endPoint.y)
        }
    }
}
