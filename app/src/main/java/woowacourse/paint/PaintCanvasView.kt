package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

class PaintCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var selectedColorInt = ContextCompat.getColor(context, default_color)
    private var selectedStrokeWidth = DEFAULT_STROKE_WIDTH

    private var path = Path()
    private var paint = createPaintWith(selectedColorInt, selectedStrokeWidth)
    private val canvasData = mutableMapOf(Pair(path, paint))

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    fun selectColorInt(
        @ColorInt value: Int,
    ) {
        selectedColorInt = value
    }

    fun selectStrokeWidth(strokeWidth: Float) {
        selectedStrokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvasData.forEach {
            canvas.drawPath(it.key, it.value)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startNewLine(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawLine(pointX, pointY)
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun startNewLine(
        pointX: Float,
        pointY: Float,
    ) {
        path = Path().apply { moveTo(pointX, pointY) }
        canvasData[path] = createPaintWith(selectedColorInt, selectedStrokeWidth)
    }

    private fun drawLine(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    private fun createPaintWith(
        colorValue: Int,
        width: Float,
    ) = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = colorValue
        strokeWidth = width
    }

    companion object {
        private val default_color = R.color.red
        private const val DEFAULT_STROKE_WIDTH = 10F
    }
}
