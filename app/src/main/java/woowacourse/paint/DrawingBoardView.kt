package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.drawing.Circle
import woowacourse.paint.drawing.Drawing2

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    //    private var currentDrawing = Rectangle(RectF(), Paint())
    private var currentDrawing = Circle(RectF(), Paint())

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
                drawings.add(currentDrawing)
//                currentDrawing.pathMoveTo(pointX, pointY)
                currentDrawing.setStartPoint(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentDrawing.pathLineTo(pointX, pointY)
                invalidate()

            }

            MotionEvent.ACTION_UP -> {
//                drawings.add(currentDrawing)
                currentDrawing = currentDrawing.copy(
                    rect = RectF(pointX, pointY, pointX, pointY)
                )
            }

            else -> return false
        }
//        invalidate()
        return true
    }

    fun setBrushThickness(thickness: Float) {
        // TODO: remove type casting
        currentDrawing = currentDrawing.copyWithPaint(thickness) as Circle
    }

    fun setBrushColor(color: Int) {
        // TODO: remove type casting
        currentDrawing = currentDrawing.copyWithPaint(color) as Circle
    }
}
