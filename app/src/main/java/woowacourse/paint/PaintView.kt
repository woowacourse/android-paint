package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

class PaintView(context: Context, attr: AttributeSet) : View(
    context,
    attr,
) {
    private val path = Path()
    private val paint = Paint()

    private var startPoint = Point()

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> startPoint = Point(pointX.toInt(), pointY.toInt())
            MotionEvent.ACTION_MOVE -> {
//                path.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
//                path.lineTo(pointX, pointY)
                path.quadTo(
                    startPoint.x.toFloat(),
                    startPoint.y.toFloat(),
                    (startPoint.x + pointX) / 2,
                    (startPoint.y + pointY) / 2,
                )
                startPoint = Point(pointX.toInt(), pointY.toInt())
                path.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
                invalidate()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    fun setupWidth(width: Float) {
        paint.strokeWidth = width
        invalidate()
    }

    fun setupColor(@ColorInt color: Int) {
        paint.color = color
        invalidate()
    }
}
