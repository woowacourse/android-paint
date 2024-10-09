package woowacourse.paint.drawingboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.BrushType
import woowacourse.paint.BrushType.Companion.brushType

class DrawingBoard(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var currentDrawing = Drawing()
    private val drawings: MutableList<Drawing> = mutableListOf(currentDrawing)

    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        currentDrawing.setupDefaultDrawing()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (drawing in drawings) {
            canvas.drawPath(drawing.path, drawing.paint)
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
                currentDrawing = currentDrawing.copy()
            }
        }
        return true
    }

    private fun actionDown(
        pointX: Float,
        pointY: Float,
    ) {
        when (brushType) {
            BrushType.PEN -> {
                addNewLine()
                currentDrawing.moveTo(pointX, pointY)
            }
            BrushType.RECTANGLE -> addNewLine()
            BrushType.CIRCLE -> addNewLine()
            BrushType.ERASER -> {
                val removeItemIndex = findRemoveItem(pointX, pointY)
                if (removeItemIndex != INVALID_INDEX) drawings.removeAt(removeItemIndex)
            }
        }
    }

    private fun actionMove(
        pointX: Float,
        pointY: Float,
    ) {
        when (brushType) {
            BrushType.PEN -> currentDrawing.lineTo(pointX, pointY)
            BrushType.RECTANGLE -> {
                currentDrawing.drawRect(startX, startY, pointX, pointY)
            }
            BrushType.CIRCLE -> {
                currentDrawing.drawCircle(startX, startY, pointX, pointY)
            }
            BrushType.ERASER -> {}
        }
        invalidate()
    }

    private fun findRemoveItem(
        x: Float,
        y: Float,
    ): Int {
        val bounds = RectF()

        for (index in drawings.indices.reversed()) {
            val drawing = drawings[index]
            drawing.computeBounds(bounds)

            if (bounds.contains(x, y)) return index
        }
        return INVALID_INDEX
    }

    fun setupStrokeWidth(strokeWidth: Float) {
        val newStrokeWidthLine = currentDrawing.updateStrokeWidth(strokeWidth)
        currentDrawing = newStrokeWidthLine
    }

    fun setupColor(color: Int) {
        val newColorLine = currentDrawing.updateColor(color)
        currentDrawing = newColorLine
    }

    fun setupStyle(style: Paint.Style) {
        val newStyle = currentDrawing.updatePaintStyle(style)
        currentDrawing = newStyle
    }

    private fun addNewLine() {
        drawings.add(currentDrawing)
    }

    companion object {
        const val INVALID_INDEX = -1
    }
}
