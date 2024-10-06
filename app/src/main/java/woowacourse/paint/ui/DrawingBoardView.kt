package woowacourse.paint.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Drawing
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.model.DrawingMode.Companion.setPaintStyle
import kotlin.math.max
import kotlin.math.min

class DrawingBoardView constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private var path = Path()
    private val drawings = mutableListOf<Drawing>()
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
        drawings.forEach { canvas.drawPath(it.path, it.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initStartPosition(event)
                addNewDrawing(event)
                drawByMode(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                drawByMode(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                path = Path()
            }

            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun initStartPosition(event: MotionEvent) {
        startX = event.x
        startY = event.y
    }

    private fun addNewDrawing(event: MotionEvent) {
        path =
            Path().apply {
                moveTo(event.x, event.y)
            }
        drawings.add(Drawing(path, Paint(paint)))
    }

    private fun drawByMode(
        currentX: Float,
        currentY: Float,
    ) {
        when (drawingMode) {
            DrawingMode.PEN -> {
                drawLine(currentX, currentY)
            }

            DrawingMode.CIRCLE -> {
                drawCircle(currentX, currentY)
            }

            DrawingMode.RECTANGLE -> {
                drawRectangle(currentX, currentY)
            }

            DrawingMode.ERASE -> {
                drawLine(currentX, currentY)
            }
        }
        invalidate()
    }

    private fun drawLine(currentX: Float, currentY: Float) {
        path.lineTo(currentX, currentY)
    }

    private fun drawCircle(currentX: Float, currentY: Float) {
        path.reset()
        path.addOval(
            startX,
            startY,
            currentX,
            currentY,
            Path.Direction.CW,
        )
    }

    private fun drawRectangle(currentX: Float, currentY: Float) {
        path.reset()
        path.addRect(
            min(startX, currentX),
            min(startY, currentY),
            max(currentX, startX),
            max(currentY, startY),
            Path.Direction.CW,
        )
    }

    fun setPaintColor(color: Int) {
        paint.color = color
    }

    fun setPaintStrokeSize(size: Float) {
        paint.strokeWidth = size
    }

    fun setDrawingMode(mode: DrawingMode) {
        drawingMode = mode
        drawingMode.setPaintStyle(paint)
    }
}
