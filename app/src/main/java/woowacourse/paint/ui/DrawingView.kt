package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.BrushStyle
import woowacourse.paint.model.BrushType

@SuppressLint("ViewConstructor")
class DrawingView(
    context: Context,
    attrs: AttributeSet? = null,
    private val brushStyle: BrushStyle,
) : View(context, attrs) {
    private var startX = 0f
    private var startY = 0f
    private val brushHistory: MutableList<Brush> =
        mutableListOf(
            Brush().apply {
                configureBrush(brushStyle = brushStyle)
            },
        )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (brush in brushHistory) {
            canvas.drawPath(brush.path, brush.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val brush = brushHistory.last()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = x
                startY = y
                brush.path.moveTo(x, y)
            }

            MotionEvent.ACTION_MOVE -> brush.move(brushStyle.brushType, startX, startY, x, y, false)

            MotionEvent.ACTION_UP -> saveBrush(brush)

            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    private fun saveBrush(brush: Brush) {
        when (brushStyle.brushType) {
            BrushType.RECTANGLE -> brushHistory.add(brush.copy(path = Path()))
            BrushType.CIRCLE -> brushHistory.add(brush.copy(path = Path()))
            else -> {}
        }
    }
}
