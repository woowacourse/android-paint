package woowacourse.paint.customView.content.rectangle

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content

class Rectangle(
    override val id: Long,
    private val rectF: RectF,
    private val paint: Paint,
) : Content() {
    override val brushType: BrushType = BrushType.Rectangle

    override fun deepCopy(): Content {
        return Rectangle(id, RectF(rectF), Paint(paint))
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                rectF.right = event.x
                rectF.bottom = event.y
            }
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }
}
