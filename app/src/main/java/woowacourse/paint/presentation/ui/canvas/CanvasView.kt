package woowacourse.paint.presentation.ui.canvas

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.presentation.ui.model.BrushModel

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val brush = Brush()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        brush.drawPath(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> brush.drawLine(event.x, event.y)
            MotionEvent.ACTION_DOWN -> brush.movePoint(event.x, event.y)

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeBrush(brush: BrushModel) {
        painting.changeColor(brush.color.value)
        painting.changeWidth(brush.width)
    }
}
