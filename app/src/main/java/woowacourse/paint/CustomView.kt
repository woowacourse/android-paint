package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class CustomView constructor(context: Context, attr: AttributeSet) : View(context, attr) {
    private val path = Path()
    private val paint = Paint()
    private var xx: Float = 0f
    private var yy: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                keepDrawing(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupPaint() {
        paint.color = Color.BLUE
        paint.strokeWidth = 50f
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
    }

    private fun startDrawing(x: Float, y: Float) {
        path.moveTo(x, y)
        xx = x
        yy = y
    }

    private fun keepDrawing(x: Float, y: Float) {
        if (abs(x - xx) >= 5 || abs(y - yy) >= 5) {
            path.quadTo(xx, yy, (x + xx) / 2, (y + yy) / 2)
            xx = x
            yy = y
        }
    }
}
