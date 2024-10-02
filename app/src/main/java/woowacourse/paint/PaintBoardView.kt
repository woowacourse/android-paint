package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes

class PaintBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawLines = mutableListOf<DrawLine>()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLines.forEach {
            paint.strokeWidth = it.strokeWidth
            paint.color = it.color
            canvas.drawPath(it.path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val nextPath = Path()
                nextPath.moveTo(event.x, event.y)
                drawLines.add(DrawLine(nextPath, paint.strokeWidth, paint.color))
            }

            MotionEvent.ACTION_MOVE -> {
                drawLines.last().path.lineTo(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStrokeWidth(strokeWidth: Float) {
        paint.strokeWidth = strokeWidth
    }

    fun setColor(
        @ColorRes color: Int,
    ) {
        paint.color = context.getColor(color)
    }

    private fun setupPaint() {
        paint.color = Color.RED
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
    }
}
