package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
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
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paletteHistory.forEach {
            it.draw(canvas)
        }
        drawingElement.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingElement.movePath(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                drawingElement.initPath(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        paletteHistory.addHistory(drawingElement.withNewPaint())
        invalidate()
        return true
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
