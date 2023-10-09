package woowacourse.paint.ui.glocanvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import kotlin.math.abs

class Circle(override val paint: Paint, override val invalidate: () -> Unit) : Drawing {
    private var prevX: Float = 0f
    private var prevY: Float = 0f
    private var cx: Float = 0f
    private var cy: Float = 0f
    private var radius: Float = 0f

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = event.x
                prevY = event.y
                true
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                val distance = event.x - prevX
                radius = abs(distance) / 2
                cx = if (distance < 0) prevX - radius else prevX + radius
                cy = if (distance < 0) prevY - radius else prevY + radius
                invalidate()
                true
            }

            else -> false
        }
    }
}
