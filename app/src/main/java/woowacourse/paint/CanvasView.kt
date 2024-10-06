package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings = mutableListOf<Drawing>()
    private var currentBrush = CanvasBrush(
        BrushType.PEN,
        CanvasPaint(this.context.getColor(DEFAULT_COLOR.colorId), DEFAULT_BRUSH_SIZE)
    )

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

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
        val currentBrush = currentBrush.startDraw(pointX, pointY)

        drawings.add(Drawing(currentBrush, currentBrush.paint))
        invalidate()
    }

    private fun drawLine(
        pointX: Float,
        pointY: Float,
    ) {
        drawings.lastOrNull()?.path?.moveBrush(pointX, pointY)?.let {
            invalidate()
        }
    }

    fun changePaintColor(colorType: ColorType) {
        currentBrush = currentBrush.changeColor(colorType, this.context)
    }

    fun changeBrushWidth(width: Float) {
        currentBrush = currentBrush.changeBrushWidth(width)
    }

    fun changeBrush(brushType: BrushType) {
        currentBrush = currentBrush.changeBrush(brushType)
    }

    companion object {
        const val DEFAULT_BRUSH_SIZE = 10f
        val DEFAULT_COLOR = ColorType.RED
    }
}
