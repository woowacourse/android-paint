package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.pen.Pen
import woowacourse.paint.model.shape.Eraser
import woowacourse.paint.model.shape.Line
import woowacourse.paint.model.shape.Oval
import woowacourse.paint.model.shape.Rectangle
import woowacourse.paint.model.shape.Shapes

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    var drawMode: DrawMode = Pen.getDrawMode()

    private val shapes: Shapes = Shapes()
    var pen: Pen = Pen.createDefaultPenInstance()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        shapes.value.forEach {
            when (it) {
                is Line -> canvas.drawPath(it.path, it.paint)
                is Rectangle -> canvas.drawRect(it.startX, it.startY, it.endX, it.endY, it.paint)
                is Oval -> canvas.drawOval(it.startX, it.startY, it.endX, it.endY, it.paint)
                is Eraser -> canvas.drawPath(it.path, it.paint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchActionDown(pointX, pointY)
            MotionEvent.ACTION_MOVE -> touchActionMove(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        return true
    }

    private fun touchActionDown(pointX: Float, pointY: Float) {
        when (drawMode) {
            DrawMode.LINE -> {
                addLine(pointX, pointY)
                invalidate()
            }
            DrawMode.RECT -> {
                addRectangle(pointX, pointY)
                invalidate()
            }
            DrawMode.CIRCLE -> {
                addCircle(pointX, pointY)
                invalidate()
            }
            DrawMode.ERASER -> {
                addEraser(pointX, pointY)
                invalidate()
            }
        }
    }

    private fun touchActionMove(pointX: Float, pointY: Float) {
        when (drawMode) {
            DrawMode.LINE -> {
                val line = shapes.last() as Line
                line.quadTo(pointX, pointY)
                invalidate()
            }
            DrawMode.RECT -> {
                val rectangle = shapes.last() as Rectangle
                rectangle.endX = pointX
                rectangle.endY = pointY
                invalidate()
            }
            DrawMode.CIRCLE -> {
                val oval = shapes.last() as Oval
                oval.endX = pointX
                oval.endY = pointY
                invalidate()
            }
            DrawMode.ERASER -> {
                val eraser = shapes.last() as Eraser
                eraser.quadTo(pointX, pointY)
                invalidate()
            }
        }
    }

    private fun addLine(pointX: Float, pointY: Float) {
        val paint = pen.getPaint()
        val addLine = Line(paint)
        shapes.add(addLine, pointX, pointY)
        addLine.moveTo(pointX, pointY)
    }

    private fun addRectangle(pointX: Float, pointY: Float) {
        val addedRectangle = Rectangle().apply {
            paint.color = pen.color
            startX = pointX
            startY = pointY
            endX = pointX
            endY = pointY
        }
        shapes.add(addedRectangle)
    }

    private fun addCircle(pointX: Float, pointY: Float) {
        val addedOval = Oval().apply {
            paint.color = pen.color
            startX = pointX
            startY = pointY
            endX = pointX
            endY = pointY
        }
        shapes.add(addedOval)
    }

    private fun addEraser(pointX: Float, pointY: Float) {
        val paint = pen.getPaint()
        val addEraserLine = Eraser(paint)
        shapes.add(addEraserLine, pointX, pointY)
        addEraserLine.moveTo(pointX, pointY)
    }
}
