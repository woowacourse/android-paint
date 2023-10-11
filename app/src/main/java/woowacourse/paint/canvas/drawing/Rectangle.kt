package woowacourse.paint.canvas.drawing

import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import woowacourse.paint.canvas.Point

class Rectangle private constructor(paint: Paint, private val invalidate: () -> Unit) :
    Drawing(paint) {
    private lateinit var startPoint: Point

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> startPoint = Point(x, y)
            MotionEvent.ACTION_MOVE -> {
                val left = if (startPoint.x < x) startPoint.x else x
                val top = if (startPoint.y > y) y else startPoint.y
                val right = if (startPoint.x < x) x else startPoint.x
                val bottom = if (startPoint.y > y) startPoint.y else y

                path.reset()
                path.addRect(left, top, right, bottom, Path.Direction.CW)
                invalidate()
            }

            else -> return true
        }
        return true
    }

    companion object {
        fun of(paint: Paint, invalidate: () -> Unit) =
            Rectangle(Paint(paint), invalidate)
    }
}
