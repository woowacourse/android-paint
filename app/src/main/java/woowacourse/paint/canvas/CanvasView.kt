package woowacourse.paint.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.canvas.drawing.Drawings

class CanvasView(context: Context, attr: AttributeSet) : View(
    context,
    attr,
) {
    private var drawingTool = DrawingTool.PEN
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
        xfermode = if (drawingTool == DrawingTool.ERASER) {
            PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            null
        }
    }
    private val drawings = Drawings()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    fun initPaint(width: Float, selectedColor: PaletteColor) {
        setupWidth(width)
        setupColor(selectedColor)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.drawAll(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawings.add(drawingTool.draw(paint, ::invalidate))
            MotionEvent.ACTION_UP -> drawings.checkLastDrawingEmpty()
            else -> super.onTouchEvent(event)
        }
        return drawings.onDrawingTouchEvent(event)
    }

    fun setupTools(selectedDrawingTool: DrawingTool) {
        drawingTool = selectedDrawingTool
        if (drawingTool == DrawingTool.ERASER) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            return
        }
        paint.xfermode = null
    }

    fun setupWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setupColor(color: PaletteColor) {
        paint.color = color.colorCode
    }

    fun eraseAll() {
        drawings.clear()
        invalidate()
    }

    fun undo() {
        drawings.undo()
        invalidate()
    }

    fun redo() {
        drawings.redo()
        invalidate()
    }
}
