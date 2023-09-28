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

class DrawingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val paletteHistory = DrawingHistory()
    private var path = Path()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initSetupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paletteHistory.history.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)
            }

            else -> super.onTouchEvent(event)
        }
        paletteHistory.addHistory(DrawingElement(path, Paint(paint)))
        invalidate()
        return true
    }

    private fun initSetupPaint() {
        paint.strokeWidth = 50.0f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
    }

    fun setStroke(value: Float) {
        path = Path()
        this.paint.strokeWidth = value
    }

    fun setColor(color: Int) {
        path = Path()
        this.paint.color = context.getColor(color)
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
