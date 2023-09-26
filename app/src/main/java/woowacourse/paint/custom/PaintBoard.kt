package woowacourse.paint.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val path = Path()
    private val paint = Paint()

    init {
        paint.color = Color.BLUE
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
            }

            else -> return false
        }
        invalidate()
        return true
    }
}
