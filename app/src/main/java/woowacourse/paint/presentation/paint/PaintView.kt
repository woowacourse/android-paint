package woowacourse.paint.presentation.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.presentation.palette.BrushType
import woowacourse.paint.presentation.palette.ColorUiModel

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lines: MutableList<Line> by lazy { mutableListOf() }
    private var currentBrushType: BrushType = DEFAULT_BRUSH_TYPE
    private var currentPath: Path = Path()
    private var currentPaint: Paint = Paint()
    private var startX = 0f
    private var startY = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initializePaint()
    }

    private fun initializePaint() {
        currentPaint.apply {
            color = ContextCompat.getColor(context, DEFAULT_COLOR.resId)
            strokeWidth = DEFAULT_STROKE
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            canvas.drawPath(line.path, line.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> down(event.x, event.y)
            MotionEvent.ACTION_MOVE -> move(event.x, event.y)
            MotionEvent.ACTION_UP -> up(event.x, event.y)
            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun down(x: Float, y: Float) {
        lines.add(Line(currentPath, currentPaint))
        startX = x
        startY = y
        currentPath.moveTo(x, y)
        invalidate()
    }

    private fun move(x: Float, y: Float) {
        when (currentBrushType) {
            BrushType.PEN -> {
                currentPath.lineTo(x, y)
                invalidate()
            }

            BrushType.RECTANGLE -> {
            }

            BrushType.CIRCLE -> {

            }

            BrushType.ERASER -> {

            }
        }
    }

    private fun up(x: Float, y: Float) {
        resetLine()
    }

    private fun resetLine() {
        currentPath = Path()
        currentPaint = Paint(currentPaint)
        invalidate()
    }

    fun changePaintColor(colorUiModel: ColorUiModel) {
        currentPaint.color = ContextCompat.getColor(context, colorUiModel.resId)
    }

    fun changeOvalSize(ovalSize: Float) {
        currentPaint.strokeWidth = ovalSize
    }

    fun changeBrushType(brushType: BrushType) {
        currentBrushType = brushType
    }

    companion object {
        private const val DEFAULT_STROKE = 10.0f
        private val DEFAULT_COLOR = ColorUiModel.RED
        private val DEFAULT_BRUSH_TYPE = BrushType.PEN
    }
}
