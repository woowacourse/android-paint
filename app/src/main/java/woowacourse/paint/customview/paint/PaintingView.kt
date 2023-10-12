package woowacourse.paint.customview.paint

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
import woowacourse.paint.customview.paint.tool.Eraser
import woowacourse.paint.customview.paint.tool.Oval
import woowacourse.paint.customview.paint.tool.PaintTool
import woowacourse.paint.customview.paint.tool.Pen
import woowacourse.paint.customview.paint.tool.Rectangle

class PaintingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    @ColorInt
    private var currentColor: Int = getColor(context, BrushColor.RED.colorRes)
    private var currentThickness: Float = DEFAULT_BRUSH_THICKNESS
    private var currentPaintTool: PaintTool = Pen(currentColor, currentThickness)

    private val _paintings: MutableList<Painting> = mutableListOf(currentPaintTool.painting)
    val paintings: List<Painting>
        get() = _paintings.map { painting ->
            painting.copy(
                path = Path(painting.path),
                paint = Paint(painting.paint),
            )
        }

    private val _paintingHistory: MutableList<Painting> = mutableListOf()
    val paintingHistory: List<Painting>
        get() = _paintingHistory.map { painting ->
            painting.copy(
                path = Path(painting.path),
                paint = Paint(painting.paint),
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
        _paintings.forEach { painting ->
            canvas.drawPath(painting.path, painting.paint)
        }

        canvas.drawPath(currentPaintTool.painting.path, currentPaintTool.painting.paint)
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
                _paintings.add(currentPaintTool.painting)
            }

            MotionEvent.ACTION_CANCEL -> {
                _paintings.add(currentPaintTool.painting)
            }

            else -> super.onTouchEvent(event)
        }
        _paintingHistory.clear()
        invalidate()
        return true
    }

    fun setBrushColor(brushColor: BrushColor) {
        currentColor = getColor(context, brushColor.colorRes)
    }

    fun setBrushThickness(thickness: Float) {
        currentThickness = thickness
    }

    fun setPaintings(paintings: List<Painting>) {
        _paintings.clear()
        _paintings.addAll(paintings)
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

    fun setPaintingHistory(newPaintHistory: List<Painting>) {
        _paintingHistory.clear()
        _paintingHistory.addAll(newPaintHistory)
    }

    fun undo() {
        _paintingHistory.add(_paintings.removeLastOrNull() ?: return)
        currentPaintTool = currentPaintTool.newInstance()
        invalidate()
    }

    fun redo() {
        _paintings.add(_paintingHistory.removeLastOrNull() ?: return)
        invalidate()
    }

    fun clear() {
        _paintings.clear()
        currentPaintTool = currentPaintTool.newInstance()
        invalidate()
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
