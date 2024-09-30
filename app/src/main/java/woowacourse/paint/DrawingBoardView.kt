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

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var currentDrawing = Drawing(Path(), Paint())
    private var brushThickness: Float = 5f

    private val drawings: MutableList<Drawing> = mutableListOf()

    init {
        currentDrawing.paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = brushThickness
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (draw in drawings) {
            canvas.drawPath(draw.path, draw.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentDrawing = currentDrawing.copy(path = Path())
                drawings.add(currentDrawing)
                currentDrawing.path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentDrawing.path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                currentDrawing = currentDrawing.copy(path = Path())
            }

            else -> return false
        }
        invalidate()
        return true
    }

    fun setBrushThickness(thickness: Float) {
        val paint = Paint(currentDrawing.paint).apply {
            strokeWidth = thickness
        }
        currentDrawing = currentDrawing.copy(paint = paint)
    }
}

private const val TAG = "DrawingBoardView"