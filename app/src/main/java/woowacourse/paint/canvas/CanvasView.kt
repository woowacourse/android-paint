package woowacourse.paint.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.BrushType
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.brush.draw.Circle
import woowacourse.paint.brush.draw.Draw
import woowacourse.paint.brush.draw.Drawn
import woowacourse.paint.brush.draw.Line
import woowacourse.paint.brush.draw.Rectangle

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    private var brush: Brush = Brush()
    private val drawn = Drawn()
    private var drawing: Draw = Line()

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
                is Circle -> canvas.drawCircle(
                    draw.currentX,
                    draw.currentY,
                    draw.currentRadius,
                    draw.paint
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
                drawing.drawing(event.x,event.y)
                invalidate()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    fun changeBrushType(brushType: BrushType) {
        brush = brush.changeType(brushType)
        setDraw()
    }

    fun changeLineWidth(width: Float) {
        brush = brush.changeWidth(width)
        setDraw()
    }

    fun changeColor(colorPalette: ColorPalette) {
        brush = brush.changeColor(colorPalette)
        setDraw()
    }

    private fun startDraw(x: Float, y: Float) {
        setDraw(x, y)
        drawn.add(drawing)
    }

    private fun setDraw(x: Float, y: Float) {
        drawing = when(brush.brushType){
            BrushType.PEN -> {
                val line = Line(brushType = BrushType.PEN,paint = createNewPaint())
                line.move(x,y)
                line
            }
            BrushType.RECTANGLE -> Rectangle(RectF(x,y,x,y),createNewPaint())
            BrushType.CIRCLE -> Circle(x,y, 0f, createNewPaint())
            BrushType.ERASER -> {
                val line = Line(brushType = BrushType.ERASER,paint = createNewPaint())
                line.move(x,y)
                line
            }
        }
    }

    private fun setDraw() {
        drawing = when(brush.brushType){
            BrushType.PEN -> Line( brushType = BrushType.PEN, paint = createNewPaint())
            BrushType.RECTANGLE -> Rectangle(paint = createNewPaint())
            BrushType.CIRCLE -> Circle(paint = createNewPaint())
            BrushType.ERASER -> Line( brushType = BrushType.ERASER, paint = createNewPaint())
        }
    }

    private fun createNewPaint(): Paint {
        return Paint().apply {
            this.color = ContextCompat.getColor(context, brush.colorPalette.colorRes)
            this.strokeWidth = brush.width
        }
    }
}
