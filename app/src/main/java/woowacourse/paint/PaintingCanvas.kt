package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.PaintingElement
import woowacourse.paint.model.PaintingHistory

class PaintingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val paletteHistory = PaintingHistory()
    private var paintingElement = PaintingElement()
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
        paintingElement.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paintingElement = paintingElement.movePath(pointX, pointY)
                previousX = pointX
                previousY = pointY
            }

            MotionEvent.ACTION_MOVE -> {
                paintingElement.initPath(previousX, previousY, pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                paletteHistory.addHistory(paintingElement.withNewPaint())
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStroke(value: Float) {
        paintingElement = paintingElement.setStroke(value)
    }

    fun setColor(color: Int) {
        paintingElement = paintingElement.setColor(context.getColor(color))
    }

    fun setBrush(brush: PaintBrush) {
        paintingElement = paintingElement.setBrush(brush.brushTool)
    }

    fun undoCanvas() {
        paletteHistory.undo()
        paintingElement = paintingElement.withNewPathPaint()
        invalidate()
    }

    fun redoCanvas() {
        paletteHistory.redo()
        paintingElement = paintingElement.withNewPathPaint()
        invalidate()
    }

    fun resetCanvas() {
        paletteHistory.clear()
        paintingElement = paintingElement.withNewPathPaint()
        invalidate()
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
