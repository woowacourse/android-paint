package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.drawing.Drawing
import woowacourse.paint.drawing.Pen

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var currentPaint = Paint()
    private var currentThickness = 5f
    private var currentDrawing: Drawing = Pen.default()

    private val drawings: MutableList<Drawing> = mutableListOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (drawing in drawings) {
            drawing.drawOn(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                updateDrawing(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentDrawing.pathLineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                currentDrawing = currentDrawing.copyPoint(pointX, pointY)
            }

            else -> return false
        }
        invalidate()
        return true
    }

    private fun updateDrawing(pointX: Float, pointY: Float) {
        currentDrawing = currentDrawing.copyPoint(pointX, pointY)
        drawings.add(currentDrawing)
        currentDrawing.setStartPoint(pointX, pointY)
    }

    fun setBrushThickness(thickness: Float) {
        currentThickness = thickness
        currentDrawing = currentDrawing.copyWithPaint(currentThickness)
    }

    fun setBrushColor(color: Int) {
        currentPaint.color = color
        currentDrawing = currentDrawing.copyWithPaint(currentPaint.color)
    }

    fun setDrawingType(drawingType: Drawing) {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        currentDrawing = drawingType
        setBrushThickness(currentThickness)
        setBrushColor(currentPaint.color)
    }
}
