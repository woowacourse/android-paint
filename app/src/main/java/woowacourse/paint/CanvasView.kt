package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView constructor(context: Context, attr: AttributeSet) : View(context, attr) {

    init {
        changeBrush(LineBrush())
    }

    private lateinit var brush: Brush

    private val brushHistory = BrushHistory()

    private var eraserMode: Boolean = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        brushHistory.drawPath(canvas)
    }

    fun setColor(color: Color) {
        brush = brush.copy(color = context.getColor(color.id))
    }

    fun setStrokeWidth(width: Float) {
        brush = brush.copy(width = width)
    }

    fun changeBrush(brush: Brush) {
        this.brush = brush
        this.setColor(Color.values().first())
        eraserMode = false
    }

    fun eraserMode() {
        eraserMode = true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (eraserMode) {
                    brushHistory.removeAt(pointX, pointY)
                    invalidate()
                    return true
                }
                brush = brush.copy()
                brushHistory.add(brush)
                brush.startDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                if (eraserMode) return true
                brush.keepDrawing(pointX, pointY)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                if (eraserMode) return true
                finishDrawing()
                invalidate()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun finishDrawing() {
        brush.finishDrawing()
    }
}
