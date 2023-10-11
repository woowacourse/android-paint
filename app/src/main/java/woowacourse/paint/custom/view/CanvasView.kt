package woowacourse.paint.custom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.now.domain.BrushWidth
import woowacourse.paint.custom.view.model.Circle
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

    private val painted = Painted()
    private var line = Line(Path(), brushUiModel.fromPaint())
    private var rectangle = Rectangle(RectF(), brushUiModel.fromPaint())
    private var circle = Circle(0f, 0f, 0f, brushUiModel.fromPaint())

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
        when (brushUiModel.brushType) {
            BrushTypeUiModel.PEN -> {
                line = Line(Path(), brushUiModel.fromPaint())
                painted.add(line)
                line.moveTo(x, y)
            }
            BrushTypeUiModel.RECTANGLE -> {
                rectangle = Rectangle(RectF(x, y, x, y), brushUiModel.fromPaint())
                painted.add(rectangle)
            }
            BrushTypeUiModel.CIRCLE -> {
                circle = Circle(x, y, 0f, brushUiModel.fromPaint())
                painted.add(circle)
            }
            BrushTypeUiModel.ERASER -> {
                line = Line(Path(), brushUiModel.fromPaint())
                line.paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                setLayerType(LAYER_TYPE_HARDWARE, null)
                painted.add(line)
                line.moveTo(x, y)
            }
        }
    }

    private fun keepDrawing(x: Float, y: Float) {
        when (brushUiModel.brushType) {
            BrushTypeUiModel.PEN -> {
                line.quadTo(x, y)
            }
            BrushTypeUiModel.RECTANGLE -> {
                rectangle = rectangle.setEndPoint(x, y)
            }
            BrushTypeUiModel.CIRCLE -> {
                circle.changeRadius(x, y)
            }
            BrushTypeUiModel.ERASER -> {
                line.quadTo(x, y)
            }
        }
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
}
