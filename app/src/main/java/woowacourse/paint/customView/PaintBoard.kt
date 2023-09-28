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
    private val drawnPaths: MutableList<DrawingPathInfo> = mutableListOf()
    private val drawingPath: Path = Path()

    var minStrokeWidth: Float = DEFAULT_MIN_STROKE_WIDTH
        set(value) {
            require(value in DEFAULT_MIN_STROKE_WIDTH..maxStrokeWidth) { "[ERROR] 펜의 최소 두께는 최대 두께 보다 작고 0이상이여야 합니다" }
            if (currentStrokeWidth < value) {
                currentStrokeWidth = value
            }
            field = value
            updateDefaultStrokeWidth()
        }

    var maxStrokeWidth: Float = DEFAULT_MAX_STROKE_WIDTH
        set(value) {
            require(value > minStrokeWidth) { "[ERROR] 펜의 최대 두께는 최소 두께 보다 커야 합니다" }
            if (currentStrokeWidth > value) {
                currentStrokeWidth = value
            }
            field = value
            updateDefaultStrokeWidth()
        }

    private var defaultStrokeWidth: Float = (minStrokeWidth + maxStrokeWidth) / 2

    @ColorInt
    var currentColor: Int = DEFAULT_COLOR
        set(value) {
            field = value
            updateNowPaint()
        }
    var currentStrokeWidth: Float = defaultStrokeWidth
        set(value) {
            require(value in minStrokeWidth..maxStrokeWidth) { "[ERROR] 펜 두께는 ${minStrokeWidth}이상 ${maxStrokeWidth}이하의 범위만 가능합니다" }
            field = value
            updateNowPaint()
        }

    private var currentPaint: Paint = getPaint(currentColor, currentStrokeWidth)

    private fun updateNowPaint() {
        currentPaint = getPaint(currentColor, currentStrokeWidth)
    }

    private fun updateDefaultStrokeWidth() {
        defaultStrokeWidth = (minStrokeWidth + maxStrokeWidth) / 2
    }

    private fun getPaint(@ColorInt colorInt: Int = Color.RED, strokeWidth: Float = 50f): Paint =
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            color = colorInt
            this.strokeWidth = strokeWidth
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawnPaths.forEach { pathInfo -> canvas?.drawPath(pathInfo.path, pathInfo.paint) }
        canvas?.drawPath(drawingPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingPath.reset()
                drawingPath.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                drawingPath.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                drawnPaths.add(
                    DrawingPathInfo(Path(drawingPath), getPaint(currentColor, currentStrokeWidth)),
                )
                drawingPath.reset()
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
        @BindingAdapter("app:paint_board_currentColor")
        fun PaintBoard.setBindingCurrentColor(@ColorInt colorInt: Int) {
            this.currentColor = colorInt
        }

        @JvmStatic
        @BindingAdapter("app:paint_board_currentStrokeWidth")
        fun PaintBoard.setBindingCurrentStrokeWidth(currentStrokeWidth: Float) {
            this.currentStrokeWidth = currentStrokeWidth
        }

        @JvmStatic
        @BindingAdapter("app:paint_board_minStrokeWidth")
        fun PaintBoard.setBindingMinStrokeWidth(strokeWidth: Float) {
            this.minStrokeWidth = strokeWidth
        }

        @JvmStatic
        @BindingAdapter("app:paint_board_maxStrokeWidth")
        fun PaintBoard.setBindingMaxStrokeWidth(strokeWidth: Float) {
            this.maxStrokeWidth = strokeWidth
        }
    }
}
