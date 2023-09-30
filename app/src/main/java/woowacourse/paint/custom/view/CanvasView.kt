package woowacourse.paint.custom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.now.domain.BrushWidth
import woowacourse.paint.custom.model.CurveLine
import woowacourse.paint.custom.model.CurveLines
import woowacourse.paint.presentation.uimodel.BrushColorUiModel

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val curveLines = CurveLines()
    private var curveLine = CurveLine(Path(), Paint())
    private var lastX = 0f
    private var lastY = 0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        curveLines.draw(canvas)
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
        curveLines.add(curveLine)
        curveLine.path.moveTo(x, y)
        lastX = x
        lastY = y
    }

    private fun keepDrawing(x: Float, y: Float) {
        curveLine.path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
        lastX = x
        lastY = y
    }

    fun changeStrokeWidth(new: BrushWidth) {
        curveLine = curveLine.changeStrokeWidth(new)
    }

    fun changeColor(new: BrushColorUiModel) {
        curveLine = curveLine.changeColor(new)
    }
}
