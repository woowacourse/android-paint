package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintingView(
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {

    private val painting = Painting()

    private var path = Path()
    private var brush = Paint().apply {
        style = Paint.Style.STROKE
        color = context.getColor(R.color.red)
        isAntiAlias = true
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        painting.drawOn(canvas)
        canvas.drawPath(path, brush)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        with(event) {
            when (action) {
                MotionEvent.ACTION_DOWN -> path.moveTo(x, y)

                MotionEvent.ACTION_MOVE -> path.lineTo(x, y)

                MotionEvent.ACTION_UP -> {
                    painting.record(PaintingRecord(path, brush))
                    path = Path()
                }
            }
        }

        invalidate()
        return true
    }

    private fun changeBrush(
        width: Float = brush.strokeWidth,
        color: Int = brush.color
    ) {
        brush = Paint(brush).apply {
            strokeWidth = width
            this.color = color
        }
    }

    fun setBrushColor(color: Int) {
        changeBrush(color = color)
    }

    fun setBrushWidth(width: Float) {
        changeBrush(width = width)
    }
}
