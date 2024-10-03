package woowacourse.paint.presentation.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.presentation.paint.model.BrushType
import woowacourse.paint.presentation.paint.model.ColorUiModel

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawingHistory: MutableList<Drawing> by lazy { mutableListOf() }
    private val undoHistory: MutableList<Drawing> by lazy { mutableListOf() }

    private var currentDrawing: Drawing = initialDrawing()
    private var startX: Float = 0f
    private var startY: Float = 0f

    private fun initialDrawing(): Drawing {
        val initialPaint =
            Paint().apply {
                color = DEFAULT_COLOR.getColor(context)
                strokeWidth = DEFAULT_STROKE
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                isAntiAlias = true
            }
        return Drawing(paint = initialPaint, brushType = DEFAULT_BRUSH_TYPE)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawingHistory.forEach { drawing ->
            canvas.drawPath(drawing.path, drawing.paint)
        }
        if (currentDrawing.shouldClearLastShape()) {
            drawingHistory.last().clear()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> down(event.x, event.y)
            MotionEvent.ACTION_MOVE -> move(event.x, event.y)
            MotionEvent.ACTION_UP -> up()
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun down(
        x: Float,
        y: Float,
    ) {
        currentDrawing.down(x, y)
        undoHistory.clear()
        drawingHistory.add(currentDrawing)
        startX = x
        startY = y
    }

    private fun move(
        x: Float,
        y: Float,
    ) {
        currentDrawing.move(startX, startY, x, y)
    }

    private fun up() {
        currentDrawing.up()
        resetDrawing()
    }

    private fun resetDrawing() {
        currentDrawing = currentDrawing.copy(path = Path())
    }

    fun changePaintColor(colorUiModel: ColorUiModel) {
        currentDrawing.changePaintColor(colorUiModel.getColor(context))
    }

    fun changeStrokeSize(strokeSize: Float) {
        currentDrawing.changeStrokeSize(strokeSize)
    }

    fun changeBrushType(brushType: BrushType) {
        currentDrawing.changeBrushType(brushType)
    }

    fun empty() {
        drawingHistory.clear()
        undoHistory.clear()
        invalidate()
    }

    fun undo() {
        if (drawingHistory.isEmpty()) return
        val lastDrawing = drawingHistory.removeLast()
        undoHistory.add(lastDrawing)
        invalidate()
    }

    fun redo() {
        if (undoHistory.isEmpty()) return
        val firstUndoDrawing = undoHistory.removeLast()
        drawingHistory.add(firstUndoDrawing)
        invalidate()
    }

    companion object {
        private val DEFAULT_BRUSH_TYPE = BrushType.PEN
        private val DEFAULT_COLOR = ColorUiModel.RED
        private const val DEFAULT_STROKE = 10.0f
    }
}
