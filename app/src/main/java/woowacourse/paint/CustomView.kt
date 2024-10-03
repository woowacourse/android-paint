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

class CustomView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val drawings: MutableMap<Path, Paint> = mutableMapOf()

    private var path = Path()
    private var paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawAllPaths(canvas)
        canvas.drawPath(path, paint)
    }

    private fun drawAllPaths(canvas: Canvas) {
        for ((path, paint) in drawings) {
            canvas.drawPath(path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                drawings[path] = Paint(paint)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }

    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }
}
