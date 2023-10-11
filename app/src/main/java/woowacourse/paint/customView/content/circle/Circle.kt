package woowacourse.paint.customView.content.circle

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content

class Circle(
    override val id: Long,
    private val mendelCircle: MendelCircle,
    private val paint: Paint,
) : Content() {
    override val brushType: BrushType = BrushType.Circle

    override fun deepCopy(): Content {
        return Circle(id, mendelCircle.deepCopy(), Paint(paint))
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                mendelCircle.endPoint.x = event.x
                mendelCircle.endPoint.y = event.y
            }
        }
    }

    override fun draw(canvas: Canvas) {
        val center = mendelCircle.centerPoint
        canvas.drawCircle(center.x, center.y, mendelCircle.radius, paint)
    }
}
