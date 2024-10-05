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
import woowacourse.paint.paintcanvas.shape.PathShape
import woowacourse.paint.paintcanvas.shape.Shape

class PaintCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var selectedColorInt = ContextCompat.getColor(context, R.color.red)
    private var selectedStrokeWidth = DEFAULT_STROKE_WIDTH
    private var selectedDiagram = Diagram.PEN

    private var shape: Shape = PathShape()
    private var paint = createNewPaint()
    private var canvasHistory: MutableList<Pair<Shape, Paint>> = mutableListOf()

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
        shape = selectedDiagram.toShape().apply {
            onActionDown(pointX, pointY)
        }
        paint = createNewPaint()
        canvasHistory.add(shape to paint)
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

            Diagram.ERASER -> {
                // to-do
            }
        }
    }

    private fun finishDrawing(
        pointX: Float,
        pointY: Float,
    ) {
        shape.onActionUp(pointX, pointY)
        canvasHistory.add(shape to paint)
    }

    private fun createNewPaint() =
        Paint().apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            color = selectedColorInt
            strokeWidth = selectedStrokeWidth
            style = if (shape is PathShape) Paint.Style.STROKE else Paint.Style.FILL
        }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 10F
    }
}
