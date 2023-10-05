package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent

class Rectangle(
    override val id: Long,
    val rectF: RectF,
    val paint: Paint,
) : Content() {
    override val brushType: BrushType = BrushType.Rectangle

    override fun deepCopy(): Content {
        return Rectangle(id, RectF(rectF), Paint(paint))
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                rectF.left = event.x
                rectF.top = event.y
                rectF.right = event.x
                rectF.bottom = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                rectF.right = event.x
                rectF.bottom = event.y
            }

            MotionEvent.ACTION_UP -> {
                rectF.right = event.x
                rectF.bottom = event.y
            }
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rectF, paint)
    }
}
