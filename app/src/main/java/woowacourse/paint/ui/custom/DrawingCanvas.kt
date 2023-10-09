package woowacourse.paint.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.drawing.Drawing
import woowacourse.paint.drawing.DrawingHistory
import woowacourse.paint.ui.PaintMode
import woowacourse.paint.ui.PathPoint

class DrawingCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private var path = Path()
    private var paint = Paint()
    private val drawingHistory = DrawingHistory()
    private var paintMode = PaintMode.PEN
    private var startPoint = PathPoint(0f, 0f)
    private var endPoint = PathPoint(0f, 0f)

    init {
        changePaintProperty(Color.RED, DEFAULT_PAINT_WIDTH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPainting(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX: Float = event.x
        val pointY: Float = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(pointX, pointY)
            MotionEvent.ACTION_MOVE -> doDrawing(pointX, pointY)
            MotionEvent.ACTION_UP -> endDrawing()
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun drawPainting(canvas: Canvas) {
        drawDrawingHistory(canvas)
        when (paintMode) {
            PaintMode.PEN -> canvas.drawPath(path, paint)
            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> canvas.drawRect(
                startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint
            )

            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> {
                manageDrawingCircle({ startPoint, radius ->
                    canvas.drawCircle(
                        startPoint.x, startPoint.y, radius, paint
                    )
                }, startPoint, endPoint)
            }

            PaintMode.ERASER -> {}
        }
    }

    private fun drawDrawingHistory(canvas: Canvas) {
        drawingHistory.drawings.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
    }

    fun changePaintColor(@ColorInt color: Int) {
        paint = Paint(paint)
        changePaintProperty(color = color)
    }

    fun changePaintWidth(width: Float) {
        paint = Paint(paint)
        changePaintProperty(width = width)
    }

    fun changePaintMode(paintMode: PaintMode) {
        this.paintMode = paintMode
    }

    private fun changePaintStyle(style: Paint.Style) {
        paint = Paint(paint)
        changePaintProperty(style = style)
    }

    private fun resetShapePoint(initX: Float, initY: Float) {
        startPoint = PathPoint(initX, initY)
        endPoint = PathPoint(initX, initY)
    }

    private fun startDrawing(pointX: Float, pointY: Float) {
        when (paintMode) {
            PaintMode.PEN -> {
                changePaintStyle(Paint.Style.STROKE)
                path.moveTo(pointX, pointY)
            }

            PaintMode.RECTANGLE, PaintMode.CIRCLE -> {
                changePaintStyle(Paint.Style.STROKE)
                resetShapePoint(pointX, pointY)
            }

            PaintMode.FILL_RECTANGLE, PaintMode.FILL_CIRCLE -> {
                changePaintStyle(Paint.Style.FILL)
                resetShapePoint(pointX, pointY)
            }

            else -> {}
        }
    }

    private fun doDrawing(pointX: Float, pointY: Float) {
        when (paintMode) {
            PaintMode.PEN -> {
                path.lineTo(pointX, pointY)
            }

            else -> {
                renewShapeEndPoint(PathPoint(pointX, pointY))
            }
        }
    }

    private fun endDrawing() {
        when (paintMode) {
            PaintMode.PEN -> {}
            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> {
                manageDrawingRectangle({ startPoint, endPoint ->
                    path.addRect(
                        startPoint.x, startPoint.y, endPoint.x, endPoint.y, Path.Direction.CW
                    )
                }, startPoint, endPoint)
            }

            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> {
                manageDrawingCircle({ startPoint, radius ->
                    path.addCircle(
                        startPoint.x, startPoint.y, radius, Path.Direction.CW
                    )
                }, startPoint, endPoint)
            }

            PaintMode.ERASER -> {}
        }

        drawingHistory.addDrawing(Drawing(path, paint))
        path = Path()
    }

    private fun renewShapeEndPoint(endPoint: PathPoint) {
        this.endPoint = endPoint
    }

    private fun manageDrawingRectangle(
        drawLogic: (startPoint: PathPoint, endPoint: PathPoint) -> Unit,
        startPoint: PathPoint,
        endPoint: PathPoint
    ) {
        if (startPoint.x < endPoint.x) {
            drawLogic(startPoint, endPoint)
        } else {
            drawLogic(endPoint, startPoint)
        }
    }

    private fun manageDrawingCircle(
        drawLogic: (startPoint: PathPoint, radius: Float) -> Unit,
        startPoint: PathPoint,
        endPoint: PathPoint
    ) {
        if (startPoint.x < endPoint.x) {
            val radius = endPoint.x - startPoint.x
            val startPoint = PathPoint(startPoint.x + radius, startPoint.y + radius)
            drawLogic(startPoint, radius)
        } else {
            val radius = startPoint.x - endPoint.x
            val startPoint = PathPoint(startPoint.x - radius, startPoint.y - radius)
            drawLogic(startPoint, radius)
        }
    }

    private fun changePaintProperty(
        @ColorInt color: Int = paint.color,
        width: Float = paint.strokeWidth,
        style: Paint.Style = paint.style
    ) {
        paint.isAntiAlias = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = width
        paint.color = color
        paint.style = style
    }

    companion object {
        const val DEFAULT_PAINT_WIDTH = 15f
    }
}
