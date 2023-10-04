package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.view.model.pen.ink.Inks
import woowacourse.paint.view.model.pen.Pen

class PaintView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private lateinit var pen: Pen
    private var inks: Inks = Inks()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPath(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startPaint(event.x, event.y)
                movePaint(event.x + EPSILON, event.y + EPSILON)
            }

            MotionEvent.ACTION_MOVE -> {
                movePaint(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                cacheCurrentPaint()
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

    private fun startPaint(pointX: Float, pointY: Float) {
        pen.startPaint(pointX, pointY)
    }

    private fun movePaint(pointX: Float, pointY: Float) {
        pen.movePaint(pointX, pointY)
    }

    private fun cacheCurrentPaint() {
        pen.cacheCurrentPaint()
    }

    private fun drawPath(canvas: Canvas) {
        inks.draw(canvas)
        pen.ink.draw(canvas)
    }

    companion object {
        private const val EPSILON = 0.01F
    }
}
