package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.pen.Pen
import woowacourse.paint.model.shape.Line
import woowacourse.paint.model.shape.Shapes

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    var drawMode: DrawMode = Pen.getDrawMode()

    private val shapes: Shapes = Shapes()
    var pen: Pen = Pen.createDefaultPenInstance()

    private var lastX: Float = 0f
    private var lastY: Float = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        shapes.value.forEach {
            when (it) {
                is Line -> canvas.drawPath(it.path, it.paint)
            }
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
        when (drawMode) {
            DrawMode.LINE -> {
                val addedLine = addLine(pointX, pointY)
                addedLine.path.moveTo(pointX, pointY)
                invalidate()
            }
            DrawMode.RECT -> {}
            DrawMode.CIRCLE -> {}
        }
    }

    private fun penMove(pointX: Float, pointY: Float) {
        when (val shape = shapes.last()) {
            is Line -> {
                val nextX = (lastX + pointX) / 2
                val nextY = (lastY + pointY) / 2
                shape.path.quadTo(lastX, lastY, nextX, nextY)
                lastX = pointX
                lastY = pointY
                invalidate()
            }
        }
    }

    private fun addLine(pointX: Float, pointY: Float): Line {
        val paint = pen.getPaint()
        val addLine = Line(paint)
        shapes.add(addLine)
        lastX = pointX
        lastY = pointY
        return addLine
    }
}
