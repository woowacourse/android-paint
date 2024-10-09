package woowacourse.paint.drawingboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
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
        val newPaint = currentDrawing.updateStrokeWidth(strokeWidth)
        currentDrawing = Drawing(Path(), newPaint)
    }

    fun setupColor(color: Int) {
        val newPaint = currentDrawing.updateColor(color)
        currentDrawing = Drawing(Path(), newPaint)
    }

    fun setupStyle(style: Paint.Style) {
        val newPaint = currentDrawing.updatePaintStyle(style)
        currentDrawing = Drawing(Path(), newPaint)
    }

    companion object {
        const val INVALID_INDEX = -1
    }
}
