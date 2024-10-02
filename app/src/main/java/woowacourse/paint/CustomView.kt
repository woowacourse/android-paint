package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintSet.Motion

class CustomView constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {


    private val drawings = mutableListOf<Drawing>()
    private var currentPaint = CanvasPaint(this.context.getColor(ColorType.RED.colorId))
    private var currentPath: Path? = null


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        currentPath?.let { path ->
            canvas.drawPath(path, currentPaint)
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val path = Path()
                path.addOval(
                    pointX,
                    pointY,
                    pointX ,
                    pointY,
                    Path.Direction.CW
                )
                currentPath = path
            }
            MotionEvent.ACTION_MOVE -> currentPath?.lineTo(pointX, pointY)
            MotionEvent.ACTION_UP -> {
                currentPath?.let {
                    drawings.add(Drawing(it, currentPaint))
                }
                invalidate()
            }
            else -> super.onTouchEvent(event)
        }
        return true
    }

    fun changePaintColor(colorType: ColorType) {
        currentPaint = currentPaint.changeColor(this.context.getColor(colorType.colorId))
    }


    companion object {
        private const val DEFAULT_BRUSH_SIZE = 50f
    }
}
