package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawingStyle

@SuppressLint("ViewConstructor")
class DrawingView(
    context: Context,
    attrs: AttributeSet?,
    drawingStyle: DrawingStyle,
) : View(context, attrs) {
    private val path = Path()
    private val paint = Paint()

    init {
        paint.color = drawingStyle.color
        paint.isAntiAlias = true
        paint.strokeWidth = drawingStyle.strokeWidth
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> path.lineTo(x, y)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }
}
