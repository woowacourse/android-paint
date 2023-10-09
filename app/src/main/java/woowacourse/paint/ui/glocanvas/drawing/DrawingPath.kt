package woowacourse.paint.ui.glocanvas.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class DrawingPath(override val paint: Paint, override val invalidate: () -> Unit) : Drawing {
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(event.x, event.y)
                invalidate()
                true
            }
            else -> false
        }
    }
}
