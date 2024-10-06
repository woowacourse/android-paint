package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.drawing.Drawing2
import woowacourse.paint.drawing.Pen

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var currentDrawing: Drawing2 = Pen.default()

    private val drawings: MutableList<Drawing2> = mutableListOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (drawing in drawings) {
            drawing.drawOn(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentDrawing = currentDrawing.copyPoint(pointX, pointY)
                drawings.add(currentDrawing)
                currentDrawing.setStartPoint(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentDrawing.pathLineTo(pointX, pointY)
//                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                currentDrawing = currentDrawing.copyPoint(pointX, pointY)
            }

            else -> return false
        }
        invalidate()
        return true
    }

    fun setBrushThickness(thickness: Float) {
        // TODO: remove type casting
        currentDrawing = currentDrawing.copyWithPaint(thickness)
    }

    fun setBrushColor(color: Int) {
        // TODO: remove type casting
        currentDrawing = currentDrawing.copyWithPaint(color)
    }

    fun setDrawingType(drawingType: Drawing2, color: Int) {
        currentDrawing = drawingType.copyWithPaint(color)
    }
}
