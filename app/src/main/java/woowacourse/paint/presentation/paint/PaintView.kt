package woowacourse.paint.presentation.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.presentation.palette.BrushType
import woowacourse.paint.presentation.palette.ColorUiModel

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val lines: MutableList<Line> by lazy { mutableListOf() }
    private var currentMoveType: Int = 0
    private var currentBrushType: BrushType = DEFAULT_BRUSH_TYPE
    private var currentPath: Path = Path()
    private var currentPaint: Paint = Paint()
    private var startX = 0f
    private var startY = 0f

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setLayerType(LAYER_TYPE_HARDWARE, null)
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
        if (currentMoveType == 1 && (currentBrushType == BrushType.RECTANGLE || currentBrushType == BrushType.CIRCLE)) {
            if (lines.isNotEmpty()) lines.last().path.reset()
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
        currentMoveType = 0
        lines.add(Line(currentPath, currentPaint))
        startX = x
        startY = y
        if (currentBrushType == BrushType.PEN || currentBrushType == BrushType.ERASER) {
            currentPath.moveTo(x, y)
            invalidate()
        }
    }

    private fun move(x: Float, y: Float) {
        currentMoveType = 1
        when (currentBrushType) {
            BrushType.PEN -> {
                currentPaint.xfermode = null
                currentPaint.style = Paint.Style.STROKE
                currentPath.lineTo(x, y)
                invalidate()
            }

            BrushType.RECTANGLE -> {
                currentPaint.xfermode = null
                currentPaint.style = Paint.Style.FILL
                currentPath.addRect(startX, startY, x, y, Path.Direction.CW)
                invalidate()
            }

            BrushType.CIRCLE -> {
                currentPaint.xfermode = null
                currentPaint.style = Paint.Style.FILL
                currentPath.addOval(startX, startY, x, y, Path.Direction.CW)
                invalidate()
            }

            BrushType.ERASER -> {
                currentPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                currentPaint.style = Paint.Style.STROKE
                currentPath.lineTo(x, y)
                invalidate()
            }
        }
    }

    private fun up(x: Float, y: Float) {
        currentMoveType = 2
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

    fun empty() {
        lines.clear()
        invalidate()
    }

    companion object {
        private const val DEFAULT_STROKE = 10.0f
        private val DEFAULT_COLOR = ColorUiModel.RED
        private val DEFAULT_BRUSH_TYPE = BrushType.PEN
    }
}
