package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: MutableMap<Path, Paint> = mutableMapOf()
    private lateinit var path: Path
    private var brush: DrawingTool = DrawingTool.PEN
    private var thickness = DEFAULT_PAINT_THICKNESS
    private var paintColor = DEFAULT_PAINT_COLOR

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                setupDrawing()
                path.moveTo(event.x, event.y)
                invalidate()
                true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
                true
            }

            MotionEvent.ACTION_UP -> {
                path.lineTo(event.x, event.y)
                invalidate()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun setupDrawing() {
        path = Path()
        val paint = Paint(brush.paint).apply {
            strokeWidth = thickness
            color = if (brush == DrawingTool.ERASER) Color.WHITE else paintColor
            if (brush == DrawingTool.HIGHLIGHTER) alpha = 50
        }
        drawings[path] = paint
    }

    fun setThickness(thickness: Float) {
        this.thickness = thickness
    }

    fun setPaintColor(color: Int) {
        paintColor = color
    }

    fun setDrawingTool(brush: DrawingTool) {
        this.brush = brush
    }

    fun setNewCanvas() {
        drawings.clear()
        invalidate()
    }

    companion object {
        private const val DEFAULT_PAINT_THICKNESS = 0f
        private const val DEFAULT_PAINT_COLOR = Color.RED
    }
}
