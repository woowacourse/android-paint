package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import woowacourse.paint.canvas.Point

class Circle private constructor(paint: Paint, private val invalidate: () -> Unit) :
    Drawing(paint) {
    private lateinit var startPoint: Point

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> startPoint = Point(x, y)
            MotionEvent.ACTION_MOVE -> {
                path.reset()
                path.addOval(startPoint.x, startPoint.y, x, y, Path.Direction.CW)
                invalidate()
            }

            else -> return true
        }
        return true
    }

    companion object {
        fun of(paint: Paint, invalidate: () -> Unit) = Circle(Paint(paint), invalidate)
    }
}
