package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.data.Drawings
import woowacourse.paint.model.Drawing
import woowacourse.paint.model.DrawingMode

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private var path = Path()
    private lateinit var drawing: Drawing
    private var drawingMode: DrawingMode = DrawingMode.PEN
    private var startX: Float = 0F
    private var startY: Float = 0F

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
        initPaint()
    }

    private fun initPaint() {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Drawings.drawing.forEach { canvas.drawPath(it.path, it.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initStartPosition(event)
                drawing = createNewDrawing(event)
                drawing.draw(startX, startY, event.x, event.y)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                drawing.draw(startX, startY, event.x, event.y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                initializePath()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun initStartPosition(event: MotionEvent) {
        startX = event.x
        startY = event.y
    }

    private fun createNewDrawing(event: MotionEvent): Drawing {
        initializePath().moveTo(event.x, event.y)
        val newDrawing = Drawing.getDrawing(path, Paint(paint), drawingMode)
        Drawings.addNewDrawing(newDrawing)
        return newDrawing
    }

    private fun initializePath(): Path {
        path = Path()
        return path
    }

    fun setPaintColor(color: Int) {
        paint.color = color
    }

    fun setPaintStrokeSize(size: Float) {
        paint.strokeWidth = size
    }

    fun setDrawingMode(mode: DrawingMode) {
        drawingMode = mode
    }
}
