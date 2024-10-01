package woowacourse.paint.drawingboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.R

class DrawingBoard(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var selectedColor: Int = ContextCompat.getColor(context, R.color.red)
    private var selectedStrokeWidth: Float = 1f

    private var line = Line(Path(), setupPaint())
    private val lines: MutableList<Line> = mutableListOf(line)

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach {
            canvas.drawPath(it.path, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> line.path.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE -> line.path.lineTo(pointX, pointY)
            else -> return super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setupStrokeWidth(strokeWidth: Float) {
        selectedStrokeWidth = strokeWidth
        addNewLine()
    }

    fun setupColor(color: Int) {
        selectedColor = color
        addNewLine()
    }

    private fun addNewLine() {
        line = Line(Path(), setupPaint())
        lines.add(line)
    }

    private fun setupPaint() =
        Paint().apply {
            color = selectedColor
            strokeCap = Cap.ROUND
            style = Paint.Style.STROKE
            strokeWidth = selectedStrokeWidth
            isAntiAlias = true
        }
}
