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

    var drawMode: DrawMode = DrawMode.getDefaultDrawMode()

    private val shapes: Shapes = Shapes()
    var pen: Pen = Pen.createDefaultPenInstance()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        shapes.value.forEach {
            when (it) {
                is Line -> canvas.drawPath(it.path, it.paint)
                is Rectangle -> canvas.drawRect(it.rectF, it.paint)
                is Oval -> canvas.drawOval(it.rectF, it.paint)
                is Eraser -> canvas.drawPath(it.path, it.paint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> addShape(pointX, pointY)
            MotionEvent.ACTION_MOVE -> moveShape(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        return true
    }

    private fun addShape(pointX: Float, pointY: Float) {
        when (drawMode) {
            DrawMode.LINE -> addLine(pointX, pointY)
            DrawMode.RECT -> addRectangle(pointX, pointY)
            DrawMode.Oval -> addOval(pointX, pointY)
            DrawMode.ERASER -> addEraser(pointX, pointY)
        }
        invalidate()
    }

    private fun moveShape(pointX: Float, pointY: Float) {
        when (shapes.last()) {
            is Line -> moveLine(pointX, pointY)
            is Rectangle -> moveRectangle(pointX, pointY)
            is Oval -> moveOval(pointX, pointY)
            is Eraser -> moveEraser(pointX, pointY)
        }
        invalidate()
    }

    private fun addLine(pointX: Float, pointY: Float) {
        val paint = pen.createPaint()
        val addedLine = Line(paint = paint)
        shapes.add(addedLine, pointX, pointY)
        addedLine.moveTo(pointX, pointY)
    }

    private fun addRectangle(pointX: Float, pointY: Float) {
        val addedRectangle = Rectangle().apply {
            paint.color = pen.color
            updatePosition(pointX, pointY, pointX, pointY)
        }
        shapes.add(addedRectangle)
    }

    private fun addOval(pointX: Float, pointY: Float) {
        val addedOval = Oval().apply {
            paint.color = pen.color
            updatePosition(pointX, pointY, pointX, pointY)
        }
        shapes.add(addedOval)
    }

    private fun addEraser(pointX: Float, pointY: Float) {
        val paint = pen.createPaint()
        val addedEraserLine = Eraser(paint = paint)
        shapes.add(addedEraserLine, pointX, pointY)
        addedEraserLine.moveTo(pointX, pointY)
    }

    private fun moveLine(pointX: Float, pointY: Float) {
        val line = shapes.last() as Line
        line.quadTo(pointX, pointY)
    }

    private fun moveRectangle(pointX: Float, pointY: Float) {
        val rectangle = shapes.last() as Rectangle
        rectangle.updatePosition(right = pointX, bottom = pointY)
    }

    private fun moveOval(pointX: Float, pointY: Float) {
        val oval = shapes.last() as Oval
        oval.updatePosition(right = pointX, bottom = pointY)
    }

    private fun moveEraser(pointX: Float, pointY: Float) {
        val eraser = shapes.last() as Eraser
        eraser.quadTo(pointX, pointY)
    }

    fun undo() {
        shapes.undo()
        invalidate()
    }

    fun redo() {
        shapes.redo()
        invalidate()
    }

    fun clear() {
        shapes.clear(this)
        invalidate()
    }
}
