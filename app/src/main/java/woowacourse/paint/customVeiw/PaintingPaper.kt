package woowacourse.paint.customVeiw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val path = Path()
    private val paint = Paint()

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
            MotionEvent.ACTION_DOWN -> path.addOval(
                pointX,
                pointY,
                pointX + OVAL_SIZE,
                pointY + OVAL_SIZE,
                Path.Direction.CW,
            )

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint() {
        paint.color = Color.RED
    }

    companion object {
        private const val OVAL_SIZE = 50
    }
}
