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
    private val pathHistory = PathHistory()

    init {
        changePaint(Color.RED, DEFAULT_PAINT_WIDTH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPathHistory(canvas)
        canvas.drawPath(path, paint)
    }

    private fun drawPathHistory(canvas: Canvas) {
        pathHistory.paths.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
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
                pathHistory.addPath(PathPaint(path, paint))
                path = Path()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changePaintColor(color: Int) {
        changePaint(color, paint.strokeWidth)
    }

    fun changePaintWidth(width: Float) {
        changePaint(paint.color, width)
    }

    private fun changePaint(color: Int, width: Float) {
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = width
        paint.color = color
    }

    companion object {
        const val DEFAULT_PAINT_WIDTH = 15f
    }
}
