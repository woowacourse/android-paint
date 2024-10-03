package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView (context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings = mutableListOf<Drawing>()
    private var currentPaint =
        CanvasPaint(this.context.getColor(DEFAULT_COLOR.colorId), DEFAULT_BRUSH_SIZE)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startLine(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawLine(pointX, pointY)
            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun startLine(
        pointX: Float,
        pointY: Float,
    ) {
        val currentPath =
            Path().apply {
                addOval(
                    pointX,
                    pointY,
                    pointX,
                    pointY,
                    Path.Direction.CW,
                )
            }

        drawings.add(Drawing(currentPath, currentPaint))
        invalidate()
    }

    private fun drawLine(
        pointX: Float,
        pointY: Float,
    ) {
        drawings.lastOrNull()?.path?.lineTo(pointX, pointY)?.let {
            invalidate()
        }
    }

    fun changePaintColor(colorType: ColorType) {
        currentPaint = currentPaint.changeColor(this.context.getColor(colorType.colorId))
    }

    fun changeBrushWidth(width: Float) {
        currentPaint = currentPaint.changeBrushWidth(width)
    }

    companion object {
        const val DEFAULT_BRUSH_SIZE = 10f
        val DEFAULT_COLOR = ColorType.RED
    }
}
