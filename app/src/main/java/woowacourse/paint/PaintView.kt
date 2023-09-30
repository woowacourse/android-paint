package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLACK
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    var penSize: Float = 5f

    private val path = Path()
    private val paint = Paint().apply {
        color = BLACK
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
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> addOval(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    private fun addOval(pointX: Float, pointY: Float) {
        path.addOval(
            pointX - penSize / 2,
            pointY - penSize / 2,
            pointX + penSize / 2,
            pointY + penSize / 2,
            Path.Direction.CW,
        )
    }
}
