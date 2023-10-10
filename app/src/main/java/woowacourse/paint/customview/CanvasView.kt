package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.BrushHistory
import woowacourse.paint.Color
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.LineBrush

class CanvasView constructor(context: Context, attr: AttributeSet) : View(context, attr) {

    private lateinit var brush: Brush

    private val brushHistory = BrushHistory()

    private var eraserMode: Boolean = false

    private var prevColor: Color = Color.values().first()
    private var prevStrokeWidth: Float = 10f

    init {
        changeBrush(LineBrush())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        brushHistory.drawPath(canvas)
    }

    fun setColor(color: Color) {
        prevColor = color
        brush = brush.copy(color = context.getColor(color.id))
    }

    fun setStrokeWidth(width: Float) {
        prevStrokeWidth = width
        brush = brush.copy(width = width)
    }

    fun changeBrush(brush: Brush) {
        this.brush = brush.copy(color = context.getColor(prevColor.id), width = prevStrokeWidth)
        eraserMode = false
    }

    fun eraserMode() {
        eraserMode = true
    }

    fun undo() {
        brushHistory.undo()
        invalidate()
    }

    fun redo() {
        brushHistory.redo()
        invalidate()
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
