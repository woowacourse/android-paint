package woowacourse.paint.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoard constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paths = mutableListOf<DrawablePath>()
    private var currentPath = Path()
    private val currentPaint = Paint()

    init {
        currentPaint.apply {
            color = Color.BLUE
            strokeWidth = DEFAULT_BRUSH_SIZE
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paths.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path()
                currentPath.moveTo(event.x, event.y)
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                paths.add(DrawablePath(currentPath, Paint(currentPaint)))
            }

            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setBrushSize(size: Float) {
        currentPaint.strokeWidth = size
    }

    companion object {
        const val DEFAULT_BRUSH_SIZE = 50f
    }
}
