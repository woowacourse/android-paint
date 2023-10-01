package woowacourse.paint.ui.glocanvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.ui.model.Drawing
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.Drawings

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: Drawings = Drawings()
    private val savedDrawings: Drawings = Drawings()
    private lateinit var path: Path
    private lateinit var drawingTool: DrawingToolModel
    private var thickness = DEFAULT_THICKNESS
    private var paintColor = DEFAULT_PAINT_COLOR

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.items.forEach { (path, paint) ->
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
        val paint = Paint(drawingTool.paint).apply {
            strokeWidth = thickness
            if (drawingTool != DrawingToolModel.ERASER) color = paintColor
            if (drawingTool == DrawingToolModel.HIGHLIGHTER) alpha = HIGHLIGHTER_OPACITY
        }
        drawings.addLast(Drawing(path, paint))
        savedDrawings.clear()
    }

    fun setThickness(thickness: Float) {
        this.thickness = thickness
    }

    fun setPaintColor(color: Int) {
        paintColor = color
    }

    fun setDrawingTool(brush: DrawingToolModel) {
        this.drawingTool = brush
    }

    fun goToPreviousDrawing() {
        val drawing = drawings.removeLast()
        drawing?.let { savedDrawings.addFirst(drawing) }
        invalidate()
    }

    fun goToNextDrawing() {
        val drawing = savedDrawings.removeFirst()
        drawing?.let { drawings.addLast(drawing) }
        invalidate()
    }

    fun setNewCanvas() {
        drawings.clear()
        savedDrawings.clear()
        invalidate()
    }

    companion object {
        private const val DEFAULT_THICKNESS = 0f
        private const val DEFAULT_PAINT_COLOR = Color.RED
        private const val HIGHLIGHTER_OPACITY = 80
    }
}
