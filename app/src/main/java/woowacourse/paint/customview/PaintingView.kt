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
    private var currentColor: Int = getColor(context, BrushColor.RED.colorRes)
    private var currentThickness: Float = DEFAULT_BRUSH_THICKNESS
    private var currentPaintTool: PaintTool = Pen(currentColor, currentThickness)

    private val _strokes: MutableList<Stroke> = mutableListOf(currentPaintTool.stroke)
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
        setLayerType(LAYER_TYPE_HARDWARE, null)
        attrs?.let { setupAttrs(attrs) }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        _strokes.forEach { stroke ->
            canvas.drawPath(stroke.path, stroke.paint)
        }

        canvas.drawPath(currentPaintTool.stroke.path, currentPaintTool.stroke.paint)
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
        val pointX: Float = event.x
        val pointY: Float = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPaintTool = currentPaintTool.newInstance()
                currentPaintTool.prepare(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPaintTool.use(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                _strokes.add(currentPaintTool.stroke)
            }

            MotionEvent.ACTION_CANCEL -> {
                _strokes.add(currentPaintTool.stroke)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
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

    fun setPaintMode(paintMode: PaintMode) {
        currentPaintTool = when (paintMode) {
            PaintMode.PEN -> Pen(currentColor, currentThickness)
            PaintMode.RECTANGLE -> Rectangle(currentColor)
            PaintMode.OVAL -> Oval(currentColor)
            PaintMode.ERASER -> Eraser(currentThickness)
        }
    }

    private fun PaintTool.newInstance() = when (this) {
        is Pen -> Pen(currentColor, currentThickness)

        is Oval -> Oval(currentColor)

        is Rectangle -> Rectangle(currentColor)

        is Eraser -> Eraser(currentThickness)
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
