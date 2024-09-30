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

class PaintBoard(context: Context, attr: AttributeSet) : View(context, attr) {
    private val lines: MutableList<Line> = mutableListOf()

    private var paintColor: Int = Color.RED
    private var paintStrokeWidth: Float = 25.0f

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> createLine(x, y)
            MotionEvent.ACTION_MOVE -> lines.last().path.lineTo(x, y)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
    }

    private fun createLine(x: Float, y: Float) {
        val path = createPath(x, y)
        val paint = createPaint()
        lines.add(Line(path, paint))
    }

    private fun createPath(
        x: Float,
        y: Float,
    ): Path {
        return Path().apply { moveTo(x, y) }
    }

    private fun createPaint(): Paint {
        return Paint().apply {
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            color = paintColor
            strokeWidth = paintStrokeWidth
        }
    }

    fun setPaintColor(color: Int) {
        paintColor = color
    }

    fun setPaintStrokeWidth(strokeWidth: Float) {
        paintStrokeWidth = strokeWidth
    }
}
