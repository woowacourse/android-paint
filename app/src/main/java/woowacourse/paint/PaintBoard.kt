package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Drawing
import woowacourse.paint.model.DrawingMode
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class PaintBoard(context: Context, attr: AttributeSet) : View(context, attr) {
    private val drawings: MutableList<Drawing> = mutableListOf()

    private lateinit var drawingMode: DrawingMode
    private var path: Path = Path()
    private var paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color = DEFAULT_PAINT_COLOR_RES
        strokeWidth = DEFAULT_STROKE_WIDTH
    }

    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = x
                startY = y

                drawings.add(Drawing(path, paint, drawingMode))
                path.moveTo(x, y)
            }

            MotionEvent.ACTION_MOVE -> {
                when (drawingMode) {
                    DrawingMode.PEN -> {
                        path.lineTo(x, y)
                    }

                    DrawingMode.SQUARE -> {
                        val rect = RectF(
                            min(startX, x),
                            min(startY, y),
                            max(x, startX),
                            max(y, startY)
                        )
                        path.addRect(
                            rect,
                            Path.Direction.CW
                        )
                    }

                    DrawingMode.CIRCLE -> {
                        val radius = sqrt(
                            (x - startX).toDouble().pow(2.0) +
                                    (y - startY).toDouble().pow(2.0)
                        ).toFloat()
                        path.addCircle(startX, startY, radius, Path.Direction.CW)
                    }

                    DrawingMode.ERASER -> {
                        path.lineTo(x, y)
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                createNewPath()
                createNewPaint()
            }

            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach { drawing ->
            canvas.drawPath(drawing.path, drawing.paint)
        }
    }

    private fun createNewPath() {
        path = Path()
    }

    private fun createNewPaint() {
        paint =
            Paint().apply {
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
                strokeCap = Paint.Cap.ROUND
                color = paint.color
                strokeWidth = paint.strokeWidth
            }

        applyDrawingMode()
    }

    fun updateDrawingMode(drawingMode: DrawingMode) {
        this.drawingMode = drawingMode

        applyDrawingMode()
    }

    fun updatePaintColor(color: Int) {
        paint.color = color
    }

    fun updatePaintStrokeWidth(strokeWidth: Float) {
        paint.strokeWidth = strokeWidth
    }

    private fun applyDrawingMode() {
        when (drawingMode) {
            DrawingMode.PEN -> {
                paint.style = Paint.Style.STROKE
                paint.xfermode = null
            }

            DrawingMode.SQUARE -> {
                paint.style = Paint.Style.FILL
                paint.xfermode = null
            }

            DrawingMode.CIRCLE -> {
                paint.style = Paint.Style.FILL
                paint.xfermode = null
            }

            DrawingMode.ERASER -> {
                paint.style = Paint.Style.STROKE
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
        }
    }

    companion object {
        val DEFAULT_DRAWING_MODE: DrawingMode = DrawingMode.PEN
        val DEFAULT_PAINT_COLOR_RES: Int = R.color.red
        const val DEFAULT_STROKE_WIDTH: Float = 30.0f
    }
}
