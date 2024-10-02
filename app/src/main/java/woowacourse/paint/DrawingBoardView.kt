package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var currentDrawing = Drawing(Path(), Paint())

    private val drawings: MutableList<Drawing> = mutableListOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (drawing in drawings) {
            drawing.drawPath(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentDrawing = currentDrawing.copy(path = Path())
                drawings.add(currentDrawing)
                currentDrawing.pathMoveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> currentDrawing.pathLineTo(pointX, pointY)

            MotionEvent.ACTION_UP -> currentDrawing = currentDrawing.copy(path = Path())

            else -> return false
        }
        invalidate()
        return true
    }

    fun setBrushThickness(thickness: Float) {
        currentDrawing = currentDrawing.copyWithPaint(thickness)
    }

    fun setBrushColor(color: Int) {
        currentDrawing = currentDrawing.copyWithPaint(color)
    }
}
