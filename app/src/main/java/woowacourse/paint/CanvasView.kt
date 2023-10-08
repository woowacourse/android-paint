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

    private val brushHistory = mutableListOf<Brush>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        brushHistory.forEach {
            it.drawPath(canvas)
        }
    }

    fun setColor(color: Color) {
        brush = brush.setColor(context.getColor(color.id))
    }

    fun setStrokeWidth(width: Float) {
        brush = brush.setStrokeWidth(width)
    }

    fun changeBrush(brush: Brush) {
        this.brush = brush
        this.setColor(Color.values().first())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brushHistory.add(brush)
                brush.startDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                brush.keepDrawing(pointX, pointY)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
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
