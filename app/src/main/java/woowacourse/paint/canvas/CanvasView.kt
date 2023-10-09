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
    private var brush = Brush.PEN
    private var paint = Paint()
    private var path = Path()

    private var startPoint: Point = Point(0f, 0f)
    private val drawings = mutableListOf<Drawing>()

    fun initPaint(width: Float, color: PaletteColor) {
        paint = getPaint(width, color.colorCode)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (brush) {
            Brush.PEN -> drawByPen(event)
            Brush.RECTANGLE, Brush.CIRCLE -> drawShape(event)
            Brush.ERASER -> TODO()
        }
        return true
    }

    private fun drawByPen(event: MotionEvent) {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> drawDot(x, y)
            MotionEvent.ACTION_MOVE -> drawLine(x, y)
            MotionEvent.ACTION_UP -> addDrawing()
            else -> super.onTouchEvent(event)
        }
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

    private fun drawShape(event: MotionEvent) {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> startPoint = Point(x, y)
            MotionEvent.ACTION_MOVE -> drawPreview(x, y)
            MotionEvent.ACTION_UP -> addDrawing()
            else -> super.onTouchEvent(event)
        }
    }

    private fun drawPreview(x: Float, y: Float) {
        path.reset()
        when (brush) {
            Brush.RECTANGLE -> path.addRect(startPoint.x, startPoint.y, x, y, Path.Direction.CW)
            Brush.CIRCLE -> path.addOval(startPoint.x, startPoint.y, x, y, Path.Direction.CW)
            else -> return
        }
        invalidate()
    }

    private fun erasePath() {
    }

    fun setupBrush(selectedBrush: Brush) {
        brush = selectedBrush
    }

    fun setupWidth(width: Float) {
        paint = getPaint(width, paint.color)
    }

    fun setupColor(color: PaletteColor) {
        paint = getPaint(paint.strokeWidth, color.colorCode)
    }

    private fun addDrawing() {
        if (path.isEmpty) return
        drawings.add(Drawing(path, paint))
        path = Path()
    }

    private fun getPaint(width: Float, @ColorInt selectedColor: Int): Paint {
        return Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            strokeJoin = Paint.Join.ROUND
            color = selectedColor
            strokeWidth = width
            strokeCap = Paint.Cap.ROUND
        }
    }
}
