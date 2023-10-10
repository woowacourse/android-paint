package woowacourse.paint.custom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.now.domain.BrushWidth
import woowacourse.paint.custom.model.CurveLine
import woowacourse.paint.custom.model.CurveLines
import woowacourse.paint.presentation.uimodel.BrushColorUiModel
import woowacourse.paint.presentation.uimodel.BrushTypeUiModel
import kotlin.math.abs
import kotlin.math.sqrt

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private var type = BrushTypeUiModel.PEN
    private val curveLines = CurveLines()
    private var curveLine = CurveLine(Path(), Paint())
    private var rectF = RectF()
    private val recFs = mutableListOf<RectF>()
    var centerX = 0f
    var centerY = 0f
    var radius = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        curveLines.draw(canvas)
        recFs.forEach {
            canvas.drawRect(it, curveLine.paint)
        }
        canvas.drawCircle(centerX, centerY, radius, curveLine.paint)
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
        when (type) {
            BrushTypeUiModel.PEN -> {
                curveLines.add(curveLine)
                curveLine.moveTo(x, y)
            }
            BrushTypeUiModel.RECTANGLE -> {
                rectF = RectF(x, y, x, y)
                recFs.add(rectF)
            }
            BrushTypeUiModel.CIRCLE -> {
                centerX = x
                centerY = y
                radius = 0f
            }
            else -> {}
        }
    }

    private fun keepDrawing(x: Float, y: Float) {
        when (type) {
            BrushTypeUiModel.PEN -> {
                curveLine.quadTo(x, y)
            }
            BrushTypeUiModel.RECTANGLE -> {
                rectF.right = x
                rectF.bottom = y
            }
            BrushTypeUiModel.CIRCLE -> {
                radius = sqrt(abs(centerX - x) * abs(centerX - x) + abs(centerY - y) + abs(centerY - y))
            }
            else -> {}
        }
    }

    fun changeStrokeWidth(new: BrushWidth) {
        curveLine = curveLine.changeStrokeWidth(new)
    }

    fun changeColor(new: BrushColorUiModel) {
        curveLine = curveLine.changeColor(new)
    }

    fun changeType(newType: BrushTypeUiModel) {
        type = newType
    }
}
