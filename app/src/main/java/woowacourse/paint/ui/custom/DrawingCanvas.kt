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
import woowacourse.paint.drawing.DrawingCircle
import woowacourse.paint.drawing.DrawingHistory
import woowacourse.paint.drawing.DrawingRectangle
import woowacourse.paint.drawing.Eraser
import woowacourse.paint.ui.PaintMode
import woowacourse.paint.ui.PathPoint

class DrawingCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private var path = Path()
    private var paint = Paint()
    private val drawingHistory = DrawingHistory()

    private var paintMode = PaintMode.PEN

    private val drawingRectangle = DrawingRectangle()
    private val drawingCircle = DrawingCircle()
    private val eraser = Eraser(drawingHistory)

    init {
        changePaintProperty(Color.RED, DEFAULT_PAINT_WIDTH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDrawingHistory(canvas)
        when (paintMode) {
            PaintMode.PEN -> canvas.drawPath(path, paint)
            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> drawingRectangle.drawShapeOnCanvas(
                canvas, paint
            )

            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> drawingCircle.drawShapeOnCanvas(
                canvas,
                paint
            )

            PaintMode.ERASER -> {}
        }
    }

    private fun drawDrawingHistory(canvas: Canvas) {
        drawingHistory.drawings.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
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

    private fun startDrawing(pointX: Float, pointY: Float) {
        when (paintMode) {
            PaintMode.PEN -> {
                changePaintStyle(Paint.Style.STROKE)
                path.moveTo(pointX, pointY)
            }

            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> {
                drawingRectangle.initPoint(PathPoint(pointX, pointY))
                changePaintStyle(if (paintMode == PaintMode.RECTANGLE) Paint.Style.STROKE else Paint.Style.FILL)
            }

            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> {
                drawingCircle.initPoint(PathPoint(pointX, pointY))
                changePaintStyle(if (paintMode == PaintMode.CIRCLE) Paint.Style.STROKE else Paint.Style.FILL)
            }

            PaintMode.ERASER -> {}
        }
    }

    private fun doDrawing(pointX: Float, pointY: Float) {
        when (paintMode) {
            PaintMode.PEN -> path.lineTo(pointX, pointY)
            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> drawingRectangle.updateEndPoint(
                PathPoint(pointX, pointY)
            )

            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> drawingCircle.updateEndPoint(
                PathPoint(
                    pointX,
                    pointY
                )
            )

            PaintMode.ERASER -> eraser.erasePath(PathPoint(pointX, pointY))
        }
    }

    private fun endDrawing() {
        when (paintMode) {
            PaintMode.PEN -> {}
            PaintMode.RECTANGLE, PaintMode.FILL_RECTANGLE -> drawingRectangle.addShapeToPath(path)
            PaintMode.CIRCLE, PaintMode.FILL_CIRCLE -> drawingCircle.addShapeToPath(path)
            PaintMode.ERASER -> {}
        }
        if (paintMode != PaintMode.ERASER) drawingHistory.addDrawing(Drawing(path, paint))
        path = Path()
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

    fun removeAllDrawings() {
        drawingHistory.removeAll()
        invalidate()
    }

    private fun changePaintStyle(style: Paint.Style) {
        paint = Paint(paint)
        changePaintProperty(style = style)
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
