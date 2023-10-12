package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.model.ColorPalette
import woowacourse.paint.model.DrawingHistory
import woowacourse.paint.model.brush.Brush
import woowacourse.paint.model.brush.BrushType
import woowacourse.paint.model.brush.PaintOptions

class BoardView(context: Context, attr: AttributeSet? = null) : View(context, attr) {

    private val drawingHistory = DrawingHistory()
    private val currentPaint = Paint()
    private lateinit var brush: Brush

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        brush.setPaintingOption(currentPaint)
        drawingHistory.drawAll(canvas)
        canvas.drawPath(brush.path, brush.paint)
        super.onDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> brush.startDrawing(event)
            MotionEvent.ACTION_MOVE -> brush.moveDrawing(event)
            MotionEvent.ACTION_UP -> brush.endDrawing(drawingHistory)
        }
        invalidate()
        return true
    }

    fun changeColor(colorPalette: ColorPalette) {
        val color = ContextCompat.getColor(context, colorPalette.color)
        currentPaint.color = color
    }

    fun setWidth(width: Float) {
        currentPaint.strokeWidth = width
    }

    fun setBrush(brushType: BrushType) {
        brush = when (brushType) {
            BrushType.PEN, BrushType.ERASER_LINE, BrushType.RECTANGLE, BrushType.CIRCLE,
            -> brushType.builder(Paint(PaintOptions.STROKE.paint))

            BrushType.FILLED_RECTANGLE, BrushType.FILLED_CIRCLE,
            -> brushType.builder(Paint(PaintOptions.FILLED.paint))
        }
    }

    fun eraseAll() {
        drawingHistory.clearAll()
        invalidate()
    }
}
