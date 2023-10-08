package woowacourse.paint.paintBoard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View

class DrawingPaper(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    private val painter = Painter()

    fun changeWidth(width: Float) {
        painter.changeBrush(width)
    }

    fun changeColor(color: Int) {
        painter.changeBrush(context.getColor(color))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        painter.drawPainting(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> {
                painter.savePainting()
                painter.drawDot(event)
            }
            ACTION_MOVE -> painter.drawLine(event)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }
}

