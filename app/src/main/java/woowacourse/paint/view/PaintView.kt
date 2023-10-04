package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.view.model.pen.Pen
import woowacourse.paint.view.model.pen.ink.Inks

class PaintView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private lateinit var pen: Pen
    private var inks: Inks = Inks()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawInks(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> pen.startPaint(event.x, event.y)
            MotionEvent.ACTION_MOVE -> pen.movePaint(event.x, event.y)
            MotionEvent.ACTION_UP -> {
                pen.cacheCurrentPaint()
                return true
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setPen(pen: Pen) {
        this.pen = pen
    }

    fun setPens(inks: Inks) {
        this.inks = inks
    }

    private fun drawInks(canvas: Canvas) {
        inks.draw(canvas)
        pen.ink.draw(canvas)
    }
}
