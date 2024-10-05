package woowacourse.paint.paintcanvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import woowacourse.paint.R
import woowacourse.paint.paintcanvas.shape.OvalShape
import woowacourse.paint.paintcanvas.shape.PathShape
import woowacourse.paint.paintcanvas.shape.RectShape
import woowacourse.paint.paintcanvas.shape.Shape

class PaintCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var selectedColorInt = ContextCompat.getColor(context, default_color)
    private var selectedStrokeWidth = DEFAULT_STROKE_WIDTH

    private var paint = createPaintWith(selectedColorInt, selectedStrokeWidth)

    private lateinit var shape: Shape
    private var canvasHistory: MutableList<Pair<Shape, Paint>> = mutableListOf()
    private var selectedDiagram = Diagram.PEN

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    fun selectColor(
        @ColorInt value: Int,
    ) {
        selectedColorInt = value
    }

    fun selectStrokeWidth(strokeWidth: Float) {
        selectedStrokeWidth = strokeWidth
    }

    fun selectDiagram(diagram: Diagram) {
        selectedDiagram = diagram
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvasHistory.forEach {
            it.first.draw(canvas, it.second)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(pointX, pointY)
            MotionEvent.ACTION_MOVE -> progressDrawing(pointX, pointY)
            MotionEvent.ACTION_UP -> finishDrawing(pointX, pointY)
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun startDrawing(
        pointX: Float,
        pointY: Float,
    ) {
        when (selectedDiagram) {
            Diagram.PEN -> {
                startLine(pointX, pointY)
            }

            Diagram.RECT -> {
                startRect(pointX, pointY)
            }

            Diagram.OVAL -> {
                startOval(pointX, pointY)
            }

            Diagram.ERASER -> TODO()
        }
    }

    private fun startLine(
        pointX: Float,
        pointY: Float,
    ) {
        shape = PathShape().apply { onActionDown(pointX, pointY) }
        paint = createPaintWith(selectedColorInt, selectedStrokeWidth)
        canvasHistory.add(shape to paint)
    }

    private fun startRect(
        pointX: Float,
        pointY: Float,
    ) {
        shape = RectShape().apply { onActionDown(pointX, pointY) }
        paint = createDiagramPaintWith(selectedColorInt)
    }

    private fun startOval(
        pointX: Float,
        pointY: Float,
    ) {
        shape = OvalShape().apply { onActionDown(pointX, pointY) }
        paint = createDiagramPaintWith(selectedColorInt)
    }

    private fun progressDrawing(
        pointX: Float,
        pointY: Float,
    ) {
        when (selectedDiagram) {
            Diagram.PEN -> {
                shape.onActionMove(pointX, pointY)
            }

            Diagram.RECT -> {
                shape.onActionMove(pointX, pointY)
                canvasHistory.drop(1)
                canvasHistory.add(shape to paint)
            }

            Diagram.OVAL -> {
                shape.onActionMove(pointX, pointY)
                canvasHistory.drop(1)
                canvasHistory.add(shape to paint)
            }

            Diagram.ERASER -> TODO()
        }
    }

    private fun finishDrawing(
        pointX: Float,
        pointY: Float,
    ) {
        shape.onActionUp(pointX, pointY)
        canvasHistory.add(shape to paint)
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

    private fun createDiagramPaintWith(colorValue: Int) =
        Paint().apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            style = Paint.Style.FILL
            color = colorValue
        }

    companion object {
        private val default_color = R.color.red
        private const val DEFAULT_STROKE_WIDTH = 10F
    }
}
