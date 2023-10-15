package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.model.drawingEngine.DrawingEngines
import woowacourse.paint.model.pen.Pen

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val drawingEngines: DrawingEngines = DrawingEngines()
    var pen: Pen = Pen.createDefaultPenInstance()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }

    private fun drawCanvas(canvas: Canvas) {
        drawingEngines.value.forEach {
            it.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> addPainting(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawPainting(pointX, pointY)
            else -> super.onTouchEvent(event)
        }

        return true
    }

    fun setDrawingMode(mode: DrawingMode) {
        drawingEngines.setDrawingMode(mode)
    }

    private fun addPainting(pointX: Float, pointY: Float) {
        drawingEngines.add(pen, pointX, pointY)
        invalidate()
    }

    private fun drawPainting(pointX: Float, pointY: Float) {
        val last = drawingEngines.last()
        last.setEndPoint(pointX, pointY)
        invalidate()
    }

    fun undo() {
        drawingEngines.undo()
        invalidate()
    }

    fun redo() {
        drawingEngines.redo()
        invalidate()
    }

    fun clear() {
        drawingEngines.clear(this)
        invalidate()
    }
}
