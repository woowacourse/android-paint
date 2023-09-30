package woowacourse.paint.custom.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.custom.model.CurveLine
import woowacourse.paint.custom.model.CurveLines

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val curveLines = CurveLines()
    private var curveLine = CurveLine(Path(), Paint())

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
                curveLines.add(curveLine)
                curveLine.path.moveTo(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                curveLine.path.lineTo(pointX, pointY)
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changeThickness(new: Float) {
        curveLine = curveLine.copy(
            path = Path(),
            paint = Paint(curveLine.paint).apply { strokeWidth = new },
        )
    }

    fun changeColor(new: Int) {
        curveLine = curveLine.copy(
            path = Path(),
            paint = Paint(curveLine.paint).apply { color = new },
        )
    }
}
