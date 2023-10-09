package woowacourse.paint.ui.glocanvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.ui.glocanvas.drawing.Circle
import woowacourse.paint.ui.glocanvas.drawing.DrawingPath
import woowacourse.paint.ui.glocanvas.drawing.Drawings
import woowacourse.paint.ui.glocanvas.drawing.Rectangle
import woowacourse.paint.ui.model.DrawingToolModel

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: Drawings = Drawings()
    private val savedDrawings: Drawings = Drawings()
    private lateinit var drawingTool: DrawingToolModel
    private var thickness = DEFAULT_THICKNESS
    private var paintColor = DEFAULT_PAINT_COLOR

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.items.forEach {
            it.onDraw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val drawing = when (drawingTool) {
                DrawingToolModel.PEN, DrawingToolModel.HIGHLIGHTER, DrawingToolModel.ERASER -> {
                    createPath()
                }

                DrawingToolModel.CIRCLE -> createCircle()

                DrawingToolModel.RECTANGLE -> createRectangle()
            }
            drawings.addLast(drawing)
            savedDrawings.clear()
        }
        return drawings.getLastDrawing().onTouchEvent(event)
    }

    private fun createPath(): DrawingPath {
        val paint = createPaint()
        return DrawingPath(paint, this::invalidate)
    }

    private fun createPaint(): Paint {
        return Paint(drawingTool.paint).apply {
            strokeWidth = thickness
            color = paintColor
            if (drawingTool == DrawingToolModel.HIGHLIGHTER) alpha = HIGHLIGHTER_OPACITY
        }
    }

    private fun createCircle(): Circle {
        val paint = createPaint()
        return Circle(paint, this::invalidate)
    }

    private fun createRectangle(): Rectangle {
        val paint = createPaint()
        return Rectangle(paint, this::invalidate)
    }

    fun setThickness(thickness: Float) {
        this.thickness = thickness
    }

    fun setPaintColor(color: Int) {
        paintColor = color
    }

    fun setDrawingTool(drawingTool: DrawingToolModel) {
        this.drawingTool = drawingTool
    }

    fun goToPreviousDrawing() {
        val drawing = drawings.removeLast()
        drawing?.let { savedDrawings.addFirst(drawing) }
        invalidate()
    }

    fun goToNextDrawing() {
        val drawing = savedDrawings.removeFirst()
        drawing?.let { drawings.addLast(drawing) }
        invalidate()
    }

    fun setNewCanvas() {
        drawings.clear()
        savedDrawings.clear()
        invalidate()
    }

    companion object {
        private const val DEFAULT_THICKNESS = 0f
        private const val DEFAULT_PAINT_COLOR = Color.RED
        private const val HIGHLIGHTER_OPACITY = 80
    }
}
