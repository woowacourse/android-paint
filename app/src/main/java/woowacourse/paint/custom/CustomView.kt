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

            if (!points[index].isStart) {
                drawPoint(canvas, points[index])
                continue
            }
            drawLine(canvas, points[index - 1], points[index])
        }
    }

    private fun drawPoint(canvas: Canvas, point: Point) {
        canvas.drawPoint(point.x, point.y, paint)
    }

    private fun drawLine(canvas: Canvas, startPoint: Point, endPoint: Point) {
        canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> { points.add(Point(pointX, pointY, false, paint.strokeWidth, paint.color)) }
            MotionEvent.ACTION_MOVE -> { points.add(Point(pointX, pointY, true, paint.strokeWidth, paint.color)) }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeThickness(new: Float) {
        paint.strokeWidth = new
    }

    fun changeColor(new: Int) {
        paint.color = new
    }
}
