package woowacourse.paint.presentation.curstomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.data.model.Brush
import woowacourse.paint.data.model.GraphicPrimitive

class PaintBoardView(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val graphicPrimitives: MutableList<GraphicPrimitive> = mutableListOf()
    private var brush: Brush = Brush.PEN
    private val paint: Paint = Paint()
    private var downPoint: Pair<Float, Float> = 0f to 0f
    private val touchEventListeners: MutableList<TouchEventListener> = mutableListOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graphicPrimitives.forEach { canvas.drawPath(it.path, it.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                brush.onActionDown(graphicPrimitives, pointX, pointY, paint)
                downPoint = pointX to pointY
            }

            MotionEvent.ACTION_MOVE -> {
                val (basePointX, basePointY) = downPoint
                brush.onActionMove(graphicPrimitives, basePointX, basePointY, pointX, pointY, paint)
            }

            else -> super.onTouchEvent(event)
        }
        touchEventListeners.forEach { it.onTouch() }
        invalidate()
        return true
    }

    fun setBrushColor(colorCode: Int) {
        paint.color = colorCode
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setBrush(brush: Brush) {
        this.brush = brush
    }

    fun addTouchEventListener(listener: TouchEventListener) {
        touchEventListeners.add(listener)
    }

    fun reset() {
        graphicPrimitives.clear()
        invalidate()
    }

    fun interface TouchEventListener {
        fun onTouch()
    }
}
