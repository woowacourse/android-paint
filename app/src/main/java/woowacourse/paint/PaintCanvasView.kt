package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

class PaintCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var selectedColorInt = ContextCompat.getColor(context, default_color)
    private var selectedStrokeWidth = DEFAULT_STROKE_WIDTH
    private var selectedDiagram = Diagram.LINE

    private var startX = 0F
    private var startY = 0F
    private var diagramPath = Path()
    private var path = Path()
    private var paint = createPaintWith(selectedColorInt, selectedStrokeWidth)
    private val canvasData = mutableListOf(path to paint)

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    fun selectColorInt(
        @ColorInt value: Int,
    ) {
        selectedColorInt = value
    }

    fun selectDiagram(diagram: Diagram) {
        selectedDiagram = diagram
    }

    fun selectStrokeWidth(strokeWidth: Float) {
        selectedStrokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvasData.forEach {
            canvas.drawPath(it.first, it.second)
        }
        canvas.drawPath(diagramPath, createPaintWith(selectedColorInt, selectedStrokeWidth))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startNewDiagram(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawLine(pointX, pointY)
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun startNewDiagram(
        pointX: Float,
        pointY: Float,
    ) {
        when (selectedDiagram) {
            Diagram.LINE -> {
                path = Path().apply { moveTo(pointX, pointY) }
                canvasData.add(path to createPaintWith(selectedColorInt, selectedStrokeWidth))
            }

            Diagram.RECT -> {
                startX = pointX
                startY = pointY
            }
        }
    }

    private fun drawLine(
        pointX: Float,
        pointY: Float,
    ) {
        when (selectedDiagram) {
            Diagram.LINE -> {
                path.lineTo(pointX, pointY)
            }

            Diagram.RECT -> {
                diagramPath =
                    Path().apply { addRect(startX, startY, pointX, pointY, Path.Direction.CW) }
            }
        }
    }

    private fun createPaintWith(
        colorValue: Int,
        width: Float,
    ) = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = colorValue
        strokeWidth = width
    }

    companion object {
        private val default_color = R.color.red
        private const val DEFAULT_STROKE_WIDTH = 10F
    }
}
