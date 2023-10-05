package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent

data class Rectangle(
    val ltPointF: PointF,
    val rbPointF: PointF,
    val paint: Paint,
) : Content {
    override val brushType: BrushType = BrushType.Rectangle

    override fun deepCopy(): Content {
        return Rectangle(
            PointF(ltPointF.x, ltPointF.y),
            PointF(rbPointF.x, rbPointF.y),
            Paint(paint),
        )
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                ltPointF.x = event.x
                ltPointF.y = event.y
                rbPointF.x = event.x
                rbPointF.y = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                rbPointF.x = event.x
                rbPointF.y = event.y
            }

            MotionEvent.ACTION_UP -> {
                rbPointF.x = event.x
                rbPointF.y = event.y
            }
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(ltPointF.x, ltPointF.y, rbPointF.x, rbPointF.y, paint)
    }
}
