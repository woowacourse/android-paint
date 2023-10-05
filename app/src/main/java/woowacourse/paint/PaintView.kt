package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.line.Line
import woowacourse.paint.model.line.Lines
import woowacourse.paint.model.pen.Pen

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val lines: Lines = Lines()
    var pen: Pen = Pen.createDefaultPenInstance()

    private var lastX: Float = 0f
    private var lastY: Float = 0f

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
        val pointX: Float = event.x
        val pointY: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> penDown(pointX, pointY)
            MotionEvent.ACTION_MOVE -> penMove(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        return true
    }

    private fun penDown(pointX: Float, pointY: Float) {
        val addedLine = addLine(pointX, pointY)
        addedLine.path.moveTo(pointX, pointY)
        invalidate()
    }

    private fun penMove(pointX: Float, pointY: Float) {
        val nextX = (lastX + pointX) / 2
        val nextY = (lastY + pointY) / 2
        lines.last().path.quadTo(lastX, lastY, nextX, nextY)
        lastX = pointX
        lastY = pointY
        invalidate()
    }

    private fun addLine(pointX: Float, pointY: Float): Line {
        val paint = pen.getPaint()
        val addLine = Line(paint)
        lines.add(addLine)
        lastX = pointX
        lastY = pointY
        return addLine
    }
}
