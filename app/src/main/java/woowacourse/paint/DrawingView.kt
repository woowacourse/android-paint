package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lines = mutableListOf<Line>()
    private var currentColor = resources.getColor(DEFAULT_STROKE_COLOR.colorRes, null)
    private var currentStrokeWidth = DEFAULT_STROKE_WIDTH

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (line in lines) {
            canvas.drawPath(line.path, line.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val newPath = Path().apply { moveTo(pointX, pointY) }
                val newPaint = createPaint()
                lines.add(Line(newPath, newPaint))
            }

            MotionEvent.ACTION_MOVE -> {
                lines.lastOrNull()?.path?.lineTo(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun createPaint(): Paint =
        Paint().apply {
            strokeWidth = currentStrokeWidth
            color = currentColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }

    fun setStrokeWidth(width: Float) {
        currentStrokeWidth = width
    }

    fun setStrokeColor(color: Int) {
        currentColor = color
    }

    companion object {
        private val DEFAULT_STROKE_COLOR = PaletteColor.RED
        const val DEFAULT_STROKE_WIDTH = 20f
    }
}
