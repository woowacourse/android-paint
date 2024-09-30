package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint().apply {
        style = Paint.Style.STROKE
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        Log.d("PaintBoard", "onDraw")
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            else -> return false
        }

        invalidate()
        return true
    }
}
