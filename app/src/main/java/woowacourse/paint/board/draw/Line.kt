package woowacourse.paint.board.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class Line(override val paint: Paint, strokeWidth: Float, private val invalidateView: () -> Unit) :
    GraphicObject() {

    private val path = Path()

    init {
        paint.apply {
            this.strokeWidth = strokeWidth
            style = Paint.Style.STROKE
        }
    }

    override fun onTouchEventAction(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)
            MotionEvent.ACTION_UP -> path.lineTo(pointX, pointY)
        }
        invalidateView()
        return true
    }

    override fun onDrawAction(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
