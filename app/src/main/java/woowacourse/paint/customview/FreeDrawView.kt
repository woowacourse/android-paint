package woowacourse.paint.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.Circle
import woowacourse.paint.model.brush.Pen
import woowacourse.paint.model.brush.Rectangle
import woowacourse.paint.model.palettecolor.PaletteColor

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var brush: Brush = Pen

    init {
        brush.updateStyle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Brush.previousDrawings.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
        if (brush == Circle || brush == Rectangle) {
            canvas.drawPath(Brush.previewDraw.first, Brush.previewDraw.second)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        val cursorX = event.x
        val cursorY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brush.onActionDown(cursorX, cursorY) { invalidate() }
            }

            MotionEvent.ACTION_MOVE -> {
                brush.onActionMove(cursorX, cursorY) { invalidate() }
            }

            MotionEvent.ACTION_UP -> {
                brush.onActionUp(cursorX, cursorY) { invalidate() }
            }

            else -> super.onTouchEvent(event)
        }

        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun updateColor(color: PaletteColor) {
        Brush.paintInstance.color = context.getColor(color.resourceId)
    }

    fun updateThickness(thickness: Float) {
        Brush.paintInstance.strokeWidth = thickness
    }

    fun setBrushType(brush: Brush) {
        this.brush = brush
        this.brush.updateStyle()
    }
}
