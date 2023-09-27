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
import androidx.core.content.ContextCompat.getColor

class PaintingView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @ColorInt
    private var currentColor: Int = getColor(context, BrushColor.BLUE.colorRes)

    private var currentStroke: Stroke = Stroke(Path(), Paint())

    private val strokes: MutableList<Stroke> = mutableListOf(currentStroke)

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        strokes.forEach { stroke ->
            canvas.drawPath(stroke.path, stroke.paint)
        }

        canvas.drawPath(currentStroke.path, currentStroke.paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentStroke = Stroke(Path(), Paint())
                setupPaint(currentStroke.paint)
                currentStroke.path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentStroke.path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                currentStroke.path.lineTo(pointX, pointY)
                strokes.add(currentStroke)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun setupPaint(paint: Paint) {
        paint.color = currentColor
        paint.isAntiAlias = true
        paint.strokeWidth = 20f
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
    }

    fun changeBrushColor(color: BrushColor) {
        val changedColor = getColor(context, color.colorRes)
        currentColor = changedColor
    }
}
