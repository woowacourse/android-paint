package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.drawingEngine.DrawingEngine
import woowacourse.paint.model.drawingEngine.DrawingEngines
import woowacourse.paint.model.drawingEngine.createDefaultDrawingEngine
import woowacourse.paint.model.pen.Pen

class PaintView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val drawingEngines: DrawingEngines = DrawingEngines()
    var selectedPen: Pen = Pen.createDefaultPenInstance()

    var selectedDrawingEngineInstantiation: (pen: Pen, pointX: Float, pointY: Float) -> DrawingEngine = ::createDefaultDrawingEngine

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawingEngines.draw(canvas)
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

    private fun addPainting(pointX: Float, pointY: Float) {
        val addedDrawingEngine = selectedDrawingEngineInstantiation(selectedPen, pointX, pointY)
        drawingEngines.add(addedDrawingEngine)
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
