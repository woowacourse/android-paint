package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.Circle
import woowacourse.paint.model.brush.Pen
import woowacourse.paint.model.brush.Rectangle
import woowacourse.paint.model.palettecolor.PaletteColor

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var brush: Brush = Pen(Paint().apply { color = Color.RED })

    init {
        brush.updateStyle(brush.copyPaint())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Brush.previousDrawings.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
        if (brush is Circle || brush is Rectangle) {
            canvas.drawPath(Brush.previewDraw.first, Brush.previewDraw.second)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
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

    fun updateColor(color: PaletteColor) {
        brush.updateColor(context.getColor(color.resourceId))
    }

    fun updateThickness(thickness: Float) {
        brush.updateThickness(thickness)
    }

    fun setBrushType(brush: Brush) {
        val paint = this.brush.copyPaint()
        this.brush = brush
        this.brush.updateStyle(paint)
    }
}
