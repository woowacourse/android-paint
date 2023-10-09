package woowacourse.paint.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView(context: Context, attr: AttributeSet) : View(
    context,
    attr,
) {
    private var brush = Brush.PEN
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
        xfermode = if (brush == Brush.ERASER) {
            PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            null
        }
    }
    private val path = Path()

    private var startPoint: Point = Point(0f, 0f)
    private val drawings = mutableListOf<Drawing>()

    private val drawingsCanceled = mutableListOf<Drawing>()

    fun initPaint(width: Float, selectedColor: PaletteColor) {
        setupWidth(width)
        setupColor(selectedColor)
    }

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
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
            Brush.PEN, Brush.ERASER -> drawLine(event)
            Brush.RECTANGLE, Brush.CIRCLE -> drawShape(event)
        }
        return true
    }

    private fun drawLine(event: MotionEvent) {
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

    private fun addDrawing() {
        if (path.isEmpty) return
        drawings.add(Drawing.of(path, paint))
        path.reset()
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

    fun setupBrush(selectedBrush: Brush) {
        brush = selectedBrush
        if (brush == Brush.ERASER) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            return
        }
        paint.xfermode = null
    }

    fun setupWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setupColor(color: PaletteColor) {
        paint.color = color.colorCode
    }

    fun eraseAll() {
        drawings.clear()
        drawingsCanceled.clear()
        path.reset()
        invalidate()
    }

    fun undo() {
        val drawing = drawings.removeLastOrNull()
        if (drawing != null) {
            drawingsCanceled.add(drawing)
            invalidate()
        }
    }

    fun redo() {
        val drawing = drawingsCanceled.removeLastOrNull()
        if (drawing != null) {
            drawings.add(drawing)
            invalidate()
        }
    }
}
