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

    private val painting = Painting()
    private var brush: BrushModel? = null

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painting.drawLines(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> painting.drawLine(event.x, event.y)
            MotionEvent.ACTION_DOWN -> painting.movePoint(event.x, event.y)
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeBrush(brush: BrushModel) {
        if (this.brush?.width != brush.width) {
            painting.changeWidth(brush.width)
        }
        if (this.brush?.color != brush.color) {
            painting.changeColor(brush.color.value)
        }
        if (this.brush?.type != brush.type) {
            painting.changeType(brush.type)
        }
        this.brush = brush
    }

    fun clear() {
        painting.clear()
        invalidate()
    }
}
