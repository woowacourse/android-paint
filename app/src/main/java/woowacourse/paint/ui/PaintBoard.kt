package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.Drawing

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val drawings = mutableListOf<Drawing>()
    private var currentPath = Path()
    private var currentBrush = Brush()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (drawing in drawings) {
            canvas.drawPath(drawing.path, drawing.paint)
        }

        canvas.drawPath(currentPath, currentBrush.toPaint())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                drawings.add(Drawing(Path(currentPath), currentBrush.toPaint()))
                currentPath.reset()
            }

            else -> return false
        }

        invalidate()
        return true
    }

    fun setBrush(brush: Brush) {
        currentBrush = brush
    }

    private fun Brush.toPaint(): Paint {
        return Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = this@toPaint.strokeWidth
            color = this@toPaint.color
        }
    }
}
