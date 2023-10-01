package woowacourse.paint.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

class CanvasView(context: Context, attr: AttributeSet) : View(
    context,
    attr,
) {
    private var path = Path()
    private var paint = Paint()
    private var startPoint: Point = Point(0f, 0f)
    private val lines = mutableListOf<Line>()

    fun initPaint(width: Float, color: PaletteColor) {
        paint = getPaint(width, color.colorCode)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawDot(x, y)
            MotionEvent.ACTION_MOVE -> drawLine(x, y)
            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun drawDot(x: Float, y: Float) {
        startPoint = Point(x, y)
        path.moveTo(startPoint.x, startPoint.y)
        path.lineTo(x, y)
        invalidate()
    }

    private fun drawLine(x: Float, y: Float) {
        path.moveTo(startPoint.x, startPoint.y)
        path.lineTo(x, y)
        startPoint = Point(x, y)
        invalidate()
    }

    fun setupWidth(width: Float) {
        addLine()
        paint = getPaint(width, paint.color)
    }

    fun setupColor(color: PaletteColor) {
        addLine()
        paint = getPaint(paint.strokeWidth, color.colorCode)
    }

    private fun addLine() {
        if (path.isEmpty) return
        lines.add(Line(path, paint))
        path = Path()
    }

    private fun getPaint(width: Float, @ColorInt selectedColor: Int): Paint {
        return Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            color = selectedColor
            strokeWidth = width
            strokeCap = Paint.Cap.ROUND
        }
    }
}
