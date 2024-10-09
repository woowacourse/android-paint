package woowacourse.paint.drawingboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.drawingboard.Drawings.drawings

class DrawingBoard(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var currentDrawing = Drawing()

    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        currentDrawing.setupDefaultDrawing()
        Drawings.addNewDrawing(currentDrawing)
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
                currentDrawing.actionDown(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                currentDrawing.actionMove(startX, startY, pointX, pointY)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                currentDrawing = currentDrawing.actionUp()
            }
        }
        return true
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

    companion object {
        const val INVALID_INDEX = -1
    }
}
