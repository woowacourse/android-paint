package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.ColorPalette
import woowacourse.paint.model.Drawing
import woowacourse.paint.model.DrawingHistory

class BoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var path = Path()
    private var paint = Paint()
    private val drawingHistory = DrawingHistory()
    private lateinit var brushType: BrushType
    private var startX = DEFAULT_COORDINATE
    private var startY = DEFAULT_COORDINATE

    fun changeColor(colorPalette: ColorPalette) {
        val color = ContextCompat.getColor(context, colorPalette.color)
        setupPaint(selectedColor = color)
    }

    fun setWidth(width: Float) {
        setupPaint(selectedWidth = width)
    }

    fun setBrush(brushType: BrushType) {
        this.brushType = brushType
    }

    private fun setupPaint(
        @ColorInt selectedColor: Int = paint.color,
        selectedWidth: Float = paint.strokeWidth,
    ) {
        paint = Paint(paint)

        paint.color = selectedColor
        paint.strokeWidth = selectedWidth
        when (brushType) {
            BrushType.PEN -> setNotFilledPaint()
            BrushType.FILLED_CIRCLE, BrushType.FILLED_RECTANGLE -> setFilledPaint()
            BrushType.CIRCLE, BrushType.RECTANGLE -> setNotFilledPaint()
            BrushType.ERASER -> {}
        }
    }

    private fun setNotFilledPaint() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            isAntiAlias = true
        }
    }

    private fun setFilledPaint() {
        paint.apply {
            style = Paint.Style.FILL
        }
    }

    fun erase() {
        drawingHistory.clearAll()
        path.reset()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawingHistory.history.forEach { (path: Path, paint: Paint) ->
            canvas.drawPath(path, paint)
        }
        setupPaint()
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(event, brushType)
            MotionEvent.ACTION_MOVE -> moveDrawing(event, brushType)
            MotionEvent.ACTION_UP -> endDrawing()
        }
        invalidate()
        return true
    }

    private fun startDrawing(event: MotionEvent, brushType: BrushType) {
        when (brushType) {
            BrushType.PEN -> {
                path.moveTo(event.x, event.y)
                path.lineTo(event.x, event.y)
            }
            else -> {
                startX = event.x
                startY = event.y
            }
        }
    }

    private fun moveDrawing(event: MotionEvent, brushType: BrushType) {
        when (brushType) {
            BrushType.PEN -> {
                path.lineTo(event.x, event.y)
            }
            BrushType.RECTANGLE, BrushType.FILLED_RECTANGLE -> {
                path.reset()
                path.addRect(startX, startY, event.x, event.y, Path.Direction.CCW)
                invalidate()
            }
            BrushType.CIRCLE, BrushType.FILLED_CIRCLE -> {
                path.reset()
                path.addCircle(startX, startY, event.x - startX, Path.Direction.CCW)
                invalidate()
            }
            BrushType.ERASER -> {}
        }
    }

    private fun endDrawing() {
        drawingHistory.add(Drawing(path, paint))
        path = Path()
    }

    companion object {
        private const val DEFAULT_COORDINATE = 0.0f
    }
}
