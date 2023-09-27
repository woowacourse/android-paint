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

class PaintBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings: MutableMap<Path, Paint> = mutableMapOf()
    private lateinit var path: Path
    private lateinit var paint: Paint

    init {
        setupPaint(DEFAULT_PAINT_THICKNESS, DEFAULT_PAINT_COLOR)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                path.moveTo(event.x, event.y)
                true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                true
            }

            MotionEvent.ACTION_UP -> {
                drawings[path] = paint
                invalidate()
                setupPaint(paint.strokeWidth, paint.color)
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun setupPaint(thickness: Float, color: Int) {
        paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
            strokeWidth = thickness
            this.color = color
        }
    }

    fun setBrushThickness(thickness: Float) {
        paint.strokeWidth = thickness
    }

    companion object {
        private const val DEFAULT_PAINT_THICKNESS = 0f
        private const val DEFAULT_PAINT_COLOR = Color.RED
    }
}
