package woowacourse.paint.presentation.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.presentation.palette.BrushType
import woowacourse.paint.presentation.palette.ColorUiModel

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lines: MutableList<Line> by lazy { mutableListOf() }
    private val undoHistory: MutableList<Line> by lazy { mutableListOf() }

    private var currentLine: Line = Line(paint = initialPaint())
    private var startX: Float = 0f
    private var startY: Float = 0f

    private fun initialPaint(): Paint {
        return Paint().apply {
            color = DEFAULT_COLOR.getColor(context)
            strokeWidth = DEFAULT_STROKE
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
        if (currentLine.shouldClearLastShape()) {
            lines.last().clear()
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
        currentLine.down(x, y)
        lines.add(currentLine)
        startX = x
        startY = y
    }

    private fun move(
        x: Float,
        y: Float,
    ) {
        currentLine.move(startX, startY, x, y)
    }

    private fun up() {
        currentLine.up()
        resetLine()
    }

    private fun resetLine() {
        currentLine = currentLine.copy(path = Path())
    }

    fun changePaintColor(colorUiModel: ColorUiModel) {
        currentLine.changePaintColor(colorUiModel.getColor(context))
    }

    fun changeStrokeSize(strokeSize: Float) {
        currentLine.changeStrokeSize(strokeSize)
    }

    fun changeBrushType(brushType: BrushType) {
        currentLine.changeBrushType(brushType)
    }

    fun empty() {
        lines.clear()
        invalidate()
    }

    fun undo() {
        if (lines.isEmpty()) return
        val lastLine = lines.removeLast()
        undoHistory.add(lastLine)
        invalidate()
    }

    fun redo() {
        if (undoHistory.isEmpty()) return
        val firstUndoLine = undoHistory.removeFirst()
        lines.add(firstUndoLine)
        invalidate()
    }

    companion object {
        private const val DEFAULT_STROKE = 10.0f
        private val DEFAULT_COLOR = ColorUiModel.RED
    }
}
