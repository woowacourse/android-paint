package woowacourse.paint.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.BrushType
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.brush.draw.Circle
import woowacourse.paint.brush.draw.Drawn
import woowacourse.paint.brush.draw.Line
import woowacourse.paint.brush.draw.Rectangle

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private val drawn = Drawn()

    init {
        changeColor(Brush.INIT_COLOR)
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        drawn.draws.forEach { draw ->
            when (draw) {
                is Line -> canvas.drawPath(draw.path, draw.paint)
                is Rectangle -> canvas.drawRect(draw.recF, draw.paint)
                is Circle ->
                    canvas.drawCircle(
                        draw.currentX,
                        draw.currentY,
                        draw.currentRadius,
                        draw.paint,
                    )
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startDraw(event.x, event.y)
                invalidate()
                true
            }

            MotionEvent.ACTION_MOVE -> {
                drawn.drawing(event.x, event.y)
                invalidate()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    fun changeBrushType(brushType: BrushType) {
        drawn.changeBrushType(brushType, createNewPaint())
    }

    fun changeLineWidth(width: Float) {
        drawn.changeLineWidth(width, createNewPaint())
    }

    fun changeColor(colorPalette: ColorPalette) {
        drawn.changeColor(colorPalette, createNewPaint())
    }

    private fun startDraw(
        x: Float,
        y: Float,
    ) {
        drawn.startDraw(x, y, createNewPaint())
    }

    private fun createNewPaint(): Paint {
        return Paint().apply {
            this.color = ContextCompat.getColor(context, drawn.getBrushColorRes())
            this.strokeWidth = drawn.getBrushWidth()
        }
    }
}
