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
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.Circle
import woowacourse.paint.model.brush.Eraser
import woowacourse.paint.model.brush.PathBrush
import woowacourse.paint.model.brush.Pen
import woowacourse.paint.model.brush.Rectangle
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint

class DrawingCanvas @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private var paint = Paint()
    private val drawingHistory = DrawingHistory()
    private var brush: Brush = Pen(paint = paint)

    init {
        paint.apply {
            isAntiAlias = true
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = DEFAULT_PAINT_WIDTH
            this.color = Color.RED
            this.style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDrawingHistory(canvas)
        if (brush is PathBrush) (brush as PathBrush).draw(canvas)
    }

    private fun drawDrawingHistory(canvas: Canvas) {
        drawingHistory.drawings.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val point = PathPoint(event.x, event.y)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> brush.startDrawing(point)
            MotionEvent.ACTION_MOVE -> brush.moveDrawing(point)
            MotionEvent.ACTION_UP -> if (brush is PathBrush) (brush as PathBrush).endDrawing(drawingHistory)
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun changePaintMode(brushTools: BrushTools) {
        this.brush = when (brushTools) {
            BrushTools.PEN -> {
                changePaintStyle(Paint.Style.STROKE)
                Pen(paint)
            }

            BrushTools.RECTANGLE -> {
                changePaintStyle(Paint.Style.STROKE)
                Rectangle(paint)
            }

            BrushTools.FILL_RECTANGLE -> {
                changePaintStyle(Paint.Style.FILL)
                Rectangle(paint)
            }

            BrushTools.CIRCLE -> {
                changePaintStyle(Paint.Style.STROKE)
                Circle(paint)
            }

            BrushTools.FILL_CIRCLE -> {
                changePaintStyle(Paint.Style.FILL)
                Circle(paint)
            }

            BrushTools.ERASER -> Eraser(drawingHistory)
        }
    }

    fun removeAllDrawings() {
        drawingHistory.removeAll()
        invalidate()
    }

    fun changePaintColor(@ColorInt color: Int) {
        paint = Paint(paint)
        changePaintProperty(color = color)
    }

    fun changePaintWidth(width: Float) {
        paint = Paint(paint)
        changePaintProperty(width = width)
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
        if (brush is PathBrush)
            (brush as PathBrush).changePaint(
                paint.apply {
                    strokeWidth = width
                    this.color = color
                    this.style = style
                }
            )
    }

    companion object {
        const val DEFAULT_PAINT_WIDTH = 15f
    }
}
