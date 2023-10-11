package woowacourse.paint.ui.glocanvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent

class Rectangle(override val paint: Paint, override val invalidate: () -> Unit) : Drawing {
    private val rectangle = RectF()

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(rectangle, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                rectangle.left = event.x
                rectangle.top = event.y
                true
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                rectangle.right = event.x
                rectangle.bottom = event.y
                invalidate()
                true
            }

            else -> false
        }
    }
}
