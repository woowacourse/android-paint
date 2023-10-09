package woowacourse.paint.ui.glocanvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import kotlin.math.abs

class Circle(override val paint: Paint, override val invalidate: () -> Unit) : Drawing {
    private var startX: Float = 0f
    private var startY: Float = 0f
    private var cx: Float = 0f
    private var cy: Float = 0f
    private var radius: Float = 0f

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                true
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                val isToLeft = (event.x - startX) < 0
                val isToTop = (event.y - startY) < 0
                radius = abs(event.x - startX) / 2
                cx = if (isToLeft) startX - radius else startX + radius
                cy = if (isToTop) startY - radius else startY + radius
                invalidate()
                true
            }

            else -> false
        }
    }
}
