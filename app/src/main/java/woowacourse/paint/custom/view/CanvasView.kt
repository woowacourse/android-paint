package woowacourse.paint.custom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.now.domain.BrushWidth
import woowacourse.paint.custom.view.model.Circle
import woowacourse.paint.custom.view.model.Drawable
import woowacourse.paint.custom.view.model.Line
import woowacourse.paint.custom.view.model.Painted
import woowacourse.paint.custom.view.model.Rectangle
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel
import woowacourse.paint.presentation.uimodel.BrushUiModel

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private var brushUiModel = BrushUiModel.fromDefault()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    private val painted = Painted()
    private var drawable: Drawable = Line(BrushTypeUiModel.PEN, 0f, 0f, brushUiModel.fromPaint())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        painted.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startDrawing(pointX, pointY)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                keepDrawing(pointX, pointY)
                invalidate()
            }
            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun startDrawing(x: Float, y: Float) {
        setDrawable(x, y)
        painted.add(drawable)
    }

    private fun setDrawable(x: Float, y: Float) {
        drawable = when (brushUiModel.brushType) {
            BrushTypeUiModel.PEN -> Line(BrushTypeUiModel.PEN, x, y, brushUiModel.fromPaint())
            BrushTypeUiModel.RECTANGLE -> Rectangle(RectF(x, y, x, y), brushUiModel.fromPaint())
            BrushTypeUiModel.CIRCLE -> Circle(x, y, 0f, brushUiModel.fromPaint())
            BrushTypeUiModel.ERASER -> Line(BrushTypeUiModel.ERASER, x, y, brushUiModel.fromPaint())
        }
    }

    private fun keepDrawing(x: Float, y: Float) {
        drawable.keepDrawing(x, y)
    }

    fun changeStrokeWidth(new: BrushWidth) {
        brushUiModel = brushUiModel.changeWidth(new)
    }

    fun changeColor(new: BrushColorUiModel) {
        brushUiModel = brushUiModel.changeColor(new)
    }

    fun changeType(new: BrushTypeUiModel) {
        brushUiModel = brushUiModel.changeType(new)
    }

    fun redo() {
        painted.redo()
        invalidate()
    }

    fun undo() {
        painted.undo()
        invalidate()
    }

    fun clear() {
        painted.clear()
        invalidate()
    }
}
