package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.line.Line
import woowacourse.paint.model.line.Lines
import woowacourse.paint.model.pen.BallpointPen
import woowacourse.paint.model.pen.Pen

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val lines: Lines = Lines()
    var pen: Pen = BallpointPen()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        lines.value.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> penDown(event)
            MotionEvent.ACTION_MOVE -> penMove(event)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    private fun penDown(event: MotionEvent) {
        addLine()
        lines.last().path.moveTo(event.x, event.y)
        penMove(event)
    }

    private fun penMove(event: MotionEvent) {
        val pointX: Float = event.x
        val pointY: Float = event.y
        lines.last().path.lineTo(pointX, pointY)
    }

    private fun addLine() {
        val paint = pen.getPaint()
        lines.add(Line(Path(), paint))
    }
}
