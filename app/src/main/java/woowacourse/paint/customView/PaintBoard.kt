package woowacourse.paint.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.R
import woowacourse.paint.customView.container.ContentContainer
import woowacourse.paint.customView.content.Content

class PaintBoard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    private val contents = ContentContainer()
    val drawnPaths: List<Content>
        get() = contents.getDrawnContents()

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

    init {
        if (attrs != null) initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaintBoard)
        this.currentColor = typedArray.getColor(R.styleable.PaintBoard_currentColor, DEFAULT_COLOR)
        this.currentStrokeWidth =
            typedArray.getFloat(R.styleable.PaintBoard_currentStrokeWidth, defaultStrokeWidth)
        this.minStrokeWidth =
            typedArray.getFloat(R.styleable.PaintBoard_minStrokeWidth, DEFAULT_MIN_STROKE_WIDTH)
        this.maxStrokeWidth =
            typedArray.getFloat(R.styleable.PaintBoard_maxStrokeWidth, DEFAULT_MAX_STROKE_WIDTH)
        typedArray.recycle()
    }

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

    fun changeDrawnPaths(paths: List<Content>) {
        contents.changeDrawnContents(paths)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        contents.drawOnCanvas(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        contents.updateContent(event, getCurrentPaint())
        invalidate()
        return true
    }

    companion object {
        private const val DEFAULT_MIN_STROKE_WIDTH = 0f
        private const val DEFAULT_MAX_STROKE_WIDTH = 100f

        @ColorInt
        private const val DEFAULT_COLOR = Color.RED
    }
}
