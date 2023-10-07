package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawingElement
import woowacourse.paint.model.DrawingHistory
import woowacourse.paint.model.PaintBrush

class DrawingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val paletteHistory = DrawingHistory()
    private var drawingElement = DrawingElement()
    private var previousX = 0f
    private var previousY = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
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
                drawingElement = drawingElement.movePath(pointX, pointY)
                previousX = pointX
                previousY = pointY
            }

            MotionEvent.ACTION_MOVE -> {
                drawingElement.initPath(previousX, previousY, pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                paletteHistory.addHistory(drawingElement.withNewPaint())
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStroke(value: Float) {
        drawingElement = drawingElement.setStroke(value)
    }

    fun setColor(color: Int) {
        drawingElement = drawingElement.setColor(context.getColor(color))
    }

    fun setBrush(brush: PaintBrush) {
        drawingElement = drawingElement.setBrush(brush.brushTool)
    }

    fun resetCanvas() {
        paletteHistory.clear()
        drawingElement = DrawingElement()
        invalidate()
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
