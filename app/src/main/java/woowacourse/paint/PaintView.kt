package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawMode
import woowacourse.paint.model.drawingEngine.DrawingEngines
import woowacourse.paint.model.drawingEngine.PathDrawingEngine
import woowacourse.paint.model.drawingEngine.ShapeDrawingEngine
import woowacourse.paint.model.drawingEngine.path.LineDrawingEngine
import woowacourse.paint.model.drawingEngine.path.PathEraserDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.OvalDrawingEngine
import woowacourse.paint.model.drawingEngine.shape.RectangleDrawingEngine
import woowacourse.paint.model.pen.Pen

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    var drawMode: DrawMode = DrawMode.getDefaultDrawMode()

    private val drawingEngines: DrawingEngines = DrawingEngines()
    var pen: Pen = Pen.createDefaultPenInstance()

    private var lastX: Float = 0f
    private var lastY: Float = 0f

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        drawingEngines.value.forEach {
            it.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> addPainting(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawPainting(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        return true
    }

    private fun addPainting(pointX: Float, pointY: Float) {
        when (drawMode) {
            DrawMode.LINE -> addLine(pointX, pointY)
            DrawMode.RECT -> addRectangle(pointX, pointY)
            DrawMode.OVAL -> addOval(pointX, pointY)
            DrawMode.ERASER -> addEraser(pointX, pointY)
        }
        invalidate()
    }

    private fun drawPainting(pointX: Float, pointY: Float) {
        when (val last = drawingEngines.last()) {
            is ShapeDrawingEngine -> last.draw(pointX, pointY)
            is PathDrawingEngine -> {
                last.draw(lastX, lastY, pointX, pointY)
                updateLastPoint(pointX, pointY)
            }
        }
        invalidate()
    }

    private fun addLine(pointX: Float, pointY: Float) {
        val paint = pen.createPaint()
        val addedLineDrawingEngine = LineDrawingEngine(paint = paint)
        drawingEngines.add(addedLineDrawingEngine, pointX, pointY)
        addedLineDrawingEngine.moveTo(pointX, pointY)
        updateLastPoint(pointX, pointY)
    }

    private fun addRectangle(pointX: Float, pointY: Float) {
        val addedRectangleDrawingEngine = RectangleDrawingEngine().apply {
            paint.color = pen.color
            changePosition(pointX, pointY, pointX, pointY)
        }
        drawingEngines.add(addedRectangleDrawingEngine)
    }

    private fun addOval(pointX: Float, pointY: Float) {
        val addedOvalDrawingEngine = OvalDrawingEngine().apply {
            paint.color = pen.color
            changePosition(pointX, pointY, pointX, pointY)
        }
        drawingEngines.add(addedOvalDrawingEngine)
    }

    private fun addEraser(pointX: Float, pointY: Float) {
        val paint = pen.createPaint()
        val addedEraserLineDrawingEngin = PathEraserDrawingEngine(paint = paint)
        drawingEngines.add(addedEraserLineDrawingEngin, pointX, pointY)
        addedEraserLineDrawingEngin.moveTo(pointX, pointY)
        updateLastPoint(pointX, pointY)
    }

    fun undo() {
        drawingEngines.undo()
        invalidate()
    }

    fun redo() {
        drawingEngines.redo()
        invalidate()
    }

    fun clear() {
        drawingEngines.clear(this)
        invalidate()
    }

    private fun updateLastPoint(x: Float, y: Float) {
        lastX = x
        lastY = y
    }
}
