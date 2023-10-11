package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.view.MotionEvent
import woowacourse.paint.canvas.Point

class Line private constructor(paint: Paint, val invalidate: () -> Unit) : Drawing(paint) {
    private lateinit var startPoint: Point

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPoint = Point(x, y)
                path.moveTo(startPoint.x, startPoint.y)
                path.lineTo(x, y)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                path.moveTo(startPoint.x, startPoint.y)
                path.lineTo(x, y)
                startPoint = Point(x, y)
                invalidate()
            }

            else -> return true
        }
        return true
    }

    companion object {
        fun of(paint: Paint, invalidate: () -> Unit) = Line(Paint(paint), invalidate)
    }
}
