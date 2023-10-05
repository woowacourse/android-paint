package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.math.min

class Circle(
    override val id: Long,
    val centerPointF: PointF,
    private var _radius: Float,
    val paint: Paint,
) : Content() {
    val radius: Float
        get() = _radius
    private val rectF: RectF = RectF()
    override val brushType: BrushType = BrushType.Circle

    override fun deepCopy(): Content {
        return Circle(id, PointF(centerPointF.x, centerPointF.y), radius, Paint(paint))
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                rectF.left = event.x
                rectF.top = event.y
                rectF.right = event.x
                rectF.bottom = event.y
                _radius = 0f
            }

            MotionEvent.ACTION_MOVE -> {
                rectF.right = event.x
                rectF.bottom = event.y
                calculateCircle()
            }

            MotionEvent.ACTION_UP -> {
                rectF.right = event.x
                rectF.bottom = event.y
                calculateCircle()
            }
        }
    }

    private fun calculateCircle() {
        calculateRadius()
        calculateCenter()
    }

    private fun calculateRadius() {
        val width = abs(rectF.left - rectF.right)
        val height = abs(rectF.top - rectF.bottom)
        _radius = min(width, height) / 2
    }

    private fun calculateCenter() {
        centerPointF.x = if (rectF.left > rectF.right) {
            rectF.left - _radius
        } else {
            rectF.left + _radius
        }

        centerPointF.y = if (rectF.top > rectF.bottom) {
            rectF.top - _radius
        } else {
            rectF.top + _radius
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(centerPointF.x, centerPointF.y, _radius, paint)
    }
}
