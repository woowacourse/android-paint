package woowacourse.paint.drawingboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.BrushType

class DrawingBoard(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var currentLine =
        Line(
            Path(),
            Paint().apply {
                color = DEFAULT_LINE_COLOR
                style = Paint.Style.STROKE
            },
        )
    private val lines: MutableList<Line> = mutableListOf(currentLine)
    private var brushType: BrushType = DEFAULT_BRUSH_TYPE

    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                actionDown(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(pointX, pointY)
            }
            MotionEvent.ACTION_UP -> {
                actionUp(pointX, pointY)
            }
        }
        return true
    }

    private fun actionDown(
        pointX: Float,
        pointY: Float,
    ) {
        addNewLine()
        if (brushType == BrushType.PEN) {
            currentLine.moveTo(pointX, pointY)
        }
    }

    private fun actionMove(
        pointX: Float,
        pointY: Float,
    ) {
        when (brushType) {
            BrushType.PEN -> currentLine.lineTo(pointX, pointY)
            BrushType.RECTANGLE -> {
                currentLine.updateRect(startX, startY, pointX, pointY)
            }
            BrushType.CIRCLE -> {
                currentLine.updateCircle(startX, startY, pointX, pointY)
            }
        }
        invalidate()
    }

    private fun actionUp(
        pointX: Float,
        pointY: Float,
    ) {
        when (brushType) {
            BrushType.PEN -> {}
            BrushType.RECTANGLE -> {
                currentLine.addRect(startX, startY, pointX, pointY)
            }
            BrushType.CIRCLE -> {}
        }
    }

    fun setupStrokeWidth(strokeWidth: Float) {
        val newStrokeWidthLine = currentLine.updateStrokeWidth(strokeWidth)
        currentLine = newStrokeWidthLine
    }

    fun setupColor(color: Int) {
        val newColorLine = currentLine.updateColor(color)
        currentLine = newColorLine
    }

    fun setupStyle(style: Paint.Style) {
        val newStyle = currentLine.updatePaintStyle(style)
        currentLine = newStyle
    }

    fun changeBrushType(brush: BrushType) {
        this.brushType = brush
    }

    private fun addNewLine() {
        lines.add(currentLine)
    }

    companion object {
        const val DEFAULT_LINE_COLOR = Color.RED
        val DEFAULT_BRUSH_TYPE = BrushType.PEN
    }
}
