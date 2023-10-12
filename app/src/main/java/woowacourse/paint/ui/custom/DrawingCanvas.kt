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
import woowacourse.paint.model.BrushTools
import woowacourse.paint.model.Eraser
import woowacourse.paint.model.drawing.Drawing
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint
import woowacourse.paint.model.shape.Circle
import woowacourse.paint.model.shape.Rectangle

class DrawingCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private var drawing = Drawing(Path(), Paint())
    private val drawingHistory = DrawingHistory()
    private var brushTools = BrushTools.PEN

    private val rectangle = Rectangle()
    private val circle = Circle()
    private val eraser = Eraser(drawingHistory)

    init {
        changePaintProperty(Color.RED, DEFAULT_PAINT_WIDTH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDrawingHistory(canvas)
        when (brushTools) {
            BrushTools.PEN -> canvas.drawPath(drawing.path, drawing.paint)
            BrushTools.RECTANGLE, BrushTools.FILL_RECTANGLE -> rectangle.drawShapeOnCanvas(
                canvas, drawing.paint
            )

            BrushTools.CIRCLE, BrushTools.FILL_CIRCLE -> circle.drawShapeOnCanvas(
                canvas, drawing.paint
            )

            BrushTools.ERASER -> Unit
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
        when (brushTools) {
            BrushTools.PEN -> {
                changePaintStyle(Paint.Style.STROKE)
                drawing.path.moveTo(pointX, pointY)
            }

            BrushTools.RECTANGLE, BrushTools.FILL_RECTANGLE -> {
                rectangle.initPoint(PathPoint(pointX, pointY))
                changePaintStyle(if (brushTools == BrushTools.RECTANGLE) Paint.Style.STROKE else Paint.Style.FILL)
            }

            BrushTools.CIRCLE, BrushTools.FILL_CIRCLE -> {
                circle.initPoint(PathPoint(pointX, pointY))
                changePaintStyle(if (brushTools == BrushTools.CIRCLE) Paint.Style.STROKE else Paint.Style.FILL)
            }

            BrushTools.ERASER -> {
                eraser.erasePath(PathPoint(pointX, pointY))
            }
        }
    }

    private fun doDrawing(pointX: Float, pointY: Float) {
        when (brushTools) {
            BrushTools.PEN -> drawing.path.lineTo(pointX, pointY)
            BrushTools.RECTANGLE, BrushTools.FILL_RECTANGLE -> rectangle.updateEndPoint(
                PathPoint(pointX, pointY)
            )

            BrushTools.CIRCLE, BrushTools.FILL_CIRCLE -> circle.updateEndPoint(
                PathPoint(pointX, pointY)
            )

            BrushTools.ERASER -> eraser.erasePath(PathPoint(pointX, pointY))
        }
    }

    private fun endDrawing() {
        when (brushTools) {
            BrushTools.PEN -> Unit
            BrushTools.RECTANGLE, BrushTools.FILL_RECTANGLE -> rectangle.addShapeToPath(drawing.path)
            BrushTools.CIRCLE, BrushTools.FILL_CIRCLE -> circle.addShapeToPath(drawing.path)
            BrushTools.ERASER -> Unit
        }
        if (brushTools != BrushTools.ERASER) drawingHistory.addDrawing(
            Drawing(
                drawing.path, drawing.paint
            )
        )
        drawing.path = Path()
    }

    fun changePaintColor(@ColorInt color: Int) {
        drawing.paint = Paint(drawing.paint)
        changePaintProperty(color = color)
    }

    fun changePaintWidth(width: Float) {
        drawing.paint = Paint(drawing.paint)
        changePaintProperty(width = width)
    }

    fun changePaintMode(brushTools: BrushTools) {
        this.brushTools = brushTools
    }

    fun removeAllDrawings() {
        drawingHistory.removeAll()
        invalidate()
    }

    private fun changePaintStyle(style: Paint.Style) {
        drawing.paint = Paint(drawing.paint)
        changePaintProperty(style = style)
    }

    private fun changePaintProperty(
        @ColorInt color: Int = drawing.paint.color,
        width: Float = drawing.paint.strokeWidth,
        style: Paint.Style = drawing.paint.style
    ) {
        drawing.paint.isAntiAlias = true
        drawing.paint.strokeJoin = Paint.Join.ROUND
        drawing.paint.strokeCap = Paint.Cap.ROUND
        drawing.paint.strokeWidth = width
        drawing.paint.color = color
        drawing.paint.style = style
    }

    companion object {
        const val DEFAULT_PAINT_WIDTH = 15f
    }
}
