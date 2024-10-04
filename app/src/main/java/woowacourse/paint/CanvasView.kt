package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Line
import woowacourse.paint.model.MyColor
import woowacourse.paint.model.Sketch

class CanvasView(
    context: Context,
    attrs: AttributeSet,
) : View(context, attrs) {
    private val lines = mutableListOf<Sketch>()
    private var currentPath = Path()
    val currentPaint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (line in lines) {
            line.draw(canvas)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        onTouchLineEvent(event)

        invalidate()
        return true
    }

    private fun onTouchLineEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path()
                currentPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                lines.add(Line(currentPath, currentPaint.color, currentPaint.strokeWidth))
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun setupPaint() {
        currentPaint.apply {
            color = MyColor.RED
            style = Paint.Style.STROKE
            strokeWidth = 10f
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }
}
