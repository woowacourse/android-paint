package woowacourse.paint.customView

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
import androidx.databinding.BindingAdapter

class PaintBoard @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
) : View(context, attr) {
    private val _drawnPaths: MutableList<DrawingPathInfo> = mutableListOf()
    val drawnPaths: List<DrawingPathInfo>
        get() = _drawnPaths.map { it.deepCopy() }

    private val _drawingPath: Path = Path()
    val drawingPath: Path
        get() = Path(_drawingPath)

    var minStrokeWidth: Float = DEFAULT_MIN_STROKE_WIDTH
        set(value) {
            require(value in DEFAULT_MIN_STROKE_WIDTH..maxStrokeWidth) { "[ERROR] 펜의 최소 두께는 최대 두께 보다 작고 0이상이여야 합니다" }
            field = value
            if (currentStrokeWidth < value) currentStrokeWidth = value
            updateDefaultStrokeWidth()
        }

    var maxStrokeWidth: Float = DEFAULT_MAX_STROKE_WIDTH
        set(value) {
            require(value > minStrokeWidth) { "[ERROR] 펜의 최대 두께는 최소 두께 보다 커야 합니다" }
            field = value
            if (currentStrokeWidth > value) currentStrokeWidth = value
            updateDefaultStrokeWidth()
        }

    private var defaultStrokeWidth: Float = (minStrokeWidth + maxStrokeWidth) / 2

    @ColorInt
    var currentColor: Int = DEFAULT_COLOR
        set(value) {
            field = value
            updateCurrentPaint()
        }
    var currentStrokeWidth: Float = defaultStrokeWidth
        set(value) {
            require(value in minStrokeWidth..maxStrokeWidth) { "[ERROR] 펜 두께는 ${minStrokeWidth}이상 ${maxStrokeWidth}이하의 범위만 가능합니다" }
            field = value
            updateCurrentPaint()
        }

    private var currentPaint: Paint = getCurrentPaint()

    private fun updateDefaultStrokeWidth() {
        defaultStrokeWidth = (minStrokeWidth + maxStrokeWidth) / 2
    }

    private fun updateCurrentPaint() {
        currentPaint = getCurrentPaint()
    }

    private fun getCurrentPaint(): Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = currentColor
        this.strokeWidth = currentStrokeWidth
    }

    fun changeDrawnPaths(paths: List<DrawingPathInfo>) {
        _drawnPaths.clear()
        _drawnPaths.addAll(paths)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        _drawnPaths.forEach { pathInfo -> canvas?.drawPath(pathInfo.path, pathInfo.paint) }
        canvas?.drawPath(_drawingPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                _drawingPath.reset()
                _drawingPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                _drawingPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                _drawnPaths.add(DrawingPathInfo(Path(_drawingPath), getCurrentPaint()))
                _drawingPath.reset()
            }
        }
        invalidate()
        return true
    }

    companion object {
        private const val DEFAULT_MIN_STROKE_WIDTH = 0f
        private const val DEFAULT_MAX_STROKE_WIDTH = 100f

        @ColorInt
        private const val DEFAULT_COLOR = Color.RED

        @JvmStatic
        @BindingAdapter("paint_board_minStrokeWidth")
        fun PaintBoard.setBindingMinStrokeWidth(strokeWidth: Float) {
            this.minStrokeWidth = strokeWidth
        }

        @JvmStatic
        @BindingAdapter("paint_board_maxStrokeWidth")
        fun PaintBoard.setBindingMaxStrokeWidth(strokeWidth: Float) {
            this.maxStrokeWidth = strokeWidth
        }

        @JvmStatic
        @BindingAdapter("paint_board_currentColor")
        fun PaintBoard.setBindingCurrentColor(@ColorInt colorInt: Int) {
            this.currentColor = colorInt
        }

        @JvmStatic
        @BindingAdapter("paint_board_currentStrokeWidth")
        fun PaintBoard.setBindingCurrentStrokeWidth(currentStrokeWidth: Float) {
            this.currentStrokeWidth = currentStrokeWidth
        }
    }
}
