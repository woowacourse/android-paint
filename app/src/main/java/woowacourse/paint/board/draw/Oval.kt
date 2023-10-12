package woowacourse.paint.board.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

class Oval(override val paint: Paint, private val invalidateView: () -> Unit) :
    GraphicObject() {

    private val path = Path()

    private var startPointX by Delegates.notNull<Float>()
    private var startPointY by Delegates.notNull<Float>()

    override fun onTouchEventAction(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> actionDownEventListener(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawOval(pointX, pointY)
            MotionEvent.ACTION_UP -> drawOval(pointX, pointY)
        }
        invalidateView()
        return true
    }

    private fun actionDownEventListener(pointX: Float, pointY: Float) {
        startPointX = pointX
        startPointY = pointY
    }

    private fun drawOval(pointX: Float, pointY: Float) {
        path.reset()
        val leftX = min(startPointX, pointX)
        val rightX = max(startPointX, pointX)
        val topY = min(startPointY, pointY)
        val bottomY = max(startPointY, pointY)
        path.addOval(leftX, topY, rightX, bottomY, Path.Direction.CW)
    }

    override fun onDrawAction(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
