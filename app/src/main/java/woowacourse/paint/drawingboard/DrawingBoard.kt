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

class DrawingBoard(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var currentLine = Line(Path(), Paint().apply { color = DEFAULT_LINE_COLOR })
    private val lines: MutableList<Line> = mutableListOf(currentLine)

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
                addNewLine()
                currentLine.moveTo(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> currentLine.lineTo(pointX, pointY)
            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupStrokeWidth(strokeWidth: Float) {
        val newStrokeWidthLine = currentLine.updateStrokeWidth(strokeWidth)
        currentLine = newStrokeWidthLine
    }

    fun setupColor(color: Int) {
        val newColorLine = currentLine.updateColor(color)
        currentLine = newColorLine
    }

    private fun addNewLine() {
        lines.add(currentLine)
    }

    companion object {
        const val DEFAULT_LINE_COLOR = Color.RED
    }
}
