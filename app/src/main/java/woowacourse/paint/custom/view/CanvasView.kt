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

class CanvasView(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {

    private val curveLines = mutableListOf<CurveLine>()
    private var curveLine = CurveLine(Path(), Paint())

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        curveLines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                curveLine.path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                curveLine.path.lineTo(pointX, pointY)
                curveLine.path.moveTo(pointX, pointY)
                curveLines.add(curveLine)
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
