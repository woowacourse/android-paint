package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView(context: Context, attr: AttributeSet) : View(
    context,
    attr,
) {
    private val path = Path()
    private val paint = Paint()

    private var startPoint = Point()

    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5f // 선의 두께를 조절할 수 있습니다.
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
                path.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
                path.lineTo(pointX, pointY)
                startPoint = Point(pointX.toInt(), pointY.toInt())
                invalidate()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }
}
