package woowacourse.paint.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val points = mutableListOf<Point>()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = points.size
        for (index in 1 until size) {
            paint.color = points[index].color
            paint.strokeWidth = points[index].strokeWidth
            canvas.drawLine(
                points[index - 1].x,
                points[index - 1].y,
                points[index].x,
                points[index].y,
                paint,
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> { points.add(Point(pointX, pointY, paint.strokeWidth, paint.color)) }
            MotionEvent.ACTION_MOVE -> { points.add(Point(pointX, pointY, paint.strokeWidth, paint.color)) }
            MotionEvent.ACTION_UP -> { points.removeLast() }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeThickness(new: Float) {
        paint.strokeWidth = new * 100
    }

    fun changeColor(new: Int) {
        paint.color = new
    }
}
