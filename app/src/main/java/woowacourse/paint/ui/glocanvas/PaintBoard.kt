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
import woowacourse.paint.ui.model.DrawingToolModel

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: ArrayDeque<Pair<Path, Paint>> = ArrayDeque()
    private val savedDrawings: ArrayDeque<Pair<Path, Paint>> = ArrayDeque()
    private lateinit var path: Path
    private var drawingTool: DrawingToolModel = DrawingToolModel.PEN
    private var thickness = -1f
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
        val paint = Paint(drawingTool.paint).apply {
            strokeWidth = thickness
            color = if (drawingTool == DrawingToolModel.ERASER) Color.WHITE else paintColor
            if (drawingTool == DrawingToolModel.HIGHLIGHTER) alpha = 80
        }
        drawings.add(path to paint)
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
        if (drawings.isNotEmpty()) {
            val drawing = drawings.removeLast()
            savedDrawings.addFirst(drawing)
            invalidate()
        }
    }

    fun goToNextDrawing() {
        if (savedDrawings.isNotEmpty()) {
            val drawing = savedDrawings.removeFirst()
            drawings.add(drawing)
            invalidate()
        }
    }

    fun setNewCanvas() {
        drawings.clear()
        savedDrawings.clear()
        invalidate()
    }

    companion object {
        private const val DEFAULT_PAINT_COLOR = Color.RED
    }
}
