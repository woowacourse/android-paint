package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import woowacourse.paint.R

class PaintingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    @ColorInt
    private var currentColor: Int = getColor(context, BrushColor.BLUE.colorRes)
    private var currentThickness: Float = DEFAULT_BRUSH_THICKNESS
    private var currentStroke: Stroke = Stroke(Path(), createInitializedPaint())

    private val _strokes: MutableList<Stroke> = mutableListOf(currentStroke)
    val strokes: List<Stroke>
        get() = _strokes.map { stroke ->
            stroke.copy(
                path = Path(stroke.path),
                paint = Paint(stroke.paint),
            )
        }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        attrs?.let { setupAttrs(attrs) }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        _strokes.forEach { stroke ->
            canvas.drawPath(stroke.path, stroke.paint)
        }

        canvas.drawPath(currentStroke.path, currentStroke.paint)
    }

    private fun setupAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaintingView)
        currentThickness =
            typedArray.getFloat(R.styleable.PaintingView_brushThickness, DEFAULT_BRUSH_THICKNESS)
        currentColor =
            typedArray.getColor(R.styleable.PaintingView_brushColor, getColor(context, R.color.red))

        typedArray.recycle()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentStroke = Stroke(Path(), createInitializedPaint())
                currentStroke.path.moveTo(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentStroke.path.lineTo(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                currentStroke.path.lineTo(pointX, pointY)
                _strokes.add(currentStroke)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun createInitializedPaint(): Paint {
        return Paint().apply {
            color = currentColor
            isAntiAlias = true
            strokeWidth = currentThickness
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }
    }

    fun setBrushColor(brushColor: BrushColor) {
        currentColor = getColor(context, brushColor.colorRes)
    }

    fun setBrushThickness(thickness: Float) {
        currentThickness = thickness
    }

    fun setStrokes(strokes: List<Stroke>) {
        _strokes.clear()
        _strokes.addAll(strokes)
        invalidate()
    }

    companion object {
        private const val DEFAULT_BRUSH_THICKNESS: Float = 10.0f

        @JvmStatic
        @BindingAdapter("brushColor")
        fun setBrushColor(paintingView: PaintingView, brushColor: BrushColor) {
            paintingView.setBrushColor(brushColor)
        }
    }
}
