package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawingElement
import woowacourse.paint.model.DrawingHistory

class DrawingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val paletteHistory = DrawingHistory()
    private var drawingElement = DrawingElement()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initSetupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paletteHistory.history.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        canvas.drawPath(drawingElement.path, drawingElement.paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingElement.movePath(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                drawingElement.initPath(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        paletteHistory.addHistory(drawingElement.copy(paint = Paint(drawingElement.paint)))
        invalidate()
        return true
    }

    private fun initSetupPaint() {
        drawingElement.paint.apply {
            strokeWidth = 50.0f
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            color = Color.RED
        }
    }

    fun setStroke(value: Float) {
        drawingElement = drawingElement.setStroke(value)
    }

    fun setColor(color: Int) {
        drawingElement = drawingElement.setColor(context.getColor(color))
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
