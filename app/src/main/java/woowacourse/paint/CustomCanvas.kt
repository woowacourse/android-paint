package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomCanvas(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var path = Path()
    private var paint = Paint()
    private val paths = mutableListOf<Pair<Path, Paint>>()

    init {
        changePaint(Color.RED, 10f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paths.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                paths.add(path to paint)
                path = Path()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeColor(color: Int) {
        changePaint(color, paint.strokeWidth)
    }

    fun changeWidth(width: Float) {
        changePaint(paint.color, width)
    }

    private fun changePaint(color: Int, width: Float) {
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = width
        paint.color = color
    }
}
