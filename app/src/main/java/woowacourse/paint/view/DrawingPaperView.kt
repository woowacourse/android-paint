package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.databinding.BindingAdapter
import woowacourse.paint.utils.dp
import woowacourse.paint.view.shape.BrushShape
import woowacourse.paint.view.shape.Circle
import woowacourse.paint.view.shape.Eraser
import woowacourse.paint.view.shape.Pencil
import woowacourse.paint.view.shape.Rectangle

class DrawingPaperView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    private var currentShape: BrushShape =
        Pencil(0f, 0f, createPaint(DEFAULT_COLOR, DEFAULT_STROKE_WIDTH), DEFAULT_STROKE_WIDTH)

    private val shapes = mutableListOf<BrushShape>()
    private val redoShapes = mutableListOf<BrushShape>()

    var currentColor: Int = DEFAULT_COLOR
        set(value) {
            field = value
            updateCurrentShape()
        }

    var currentStrokeWidth: Float = DEFAULT_STROKE_WIDTH
        set(value) {
            field = value
            updateCurrentShape()
        }

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    private fun createPaint(
        color: Int,
        strokeWidth: Float,
    ): Paint {
        return Paint().apply {
            this.color = color
            this.strokeWidth = strokeWidth
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
        }
    }

    private fun updateCurrentShape() {
        currentShape = currentShape.copy(
            paint = createPaint(currentColor, currentStrokeWidth),
            strokeWidth = currentStrokeWidth
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (shape in shapes) {
            shape.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val shape = currentShape.copy(x = x, y = y)
                shapes.add(shape)
                redoShapes.clear()
                currentShape = shape
            }

            MotionEvent.ACTION_MOVE -> {
                val shape = currentShape
                shape.updatePosition(x, y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {}
        }
        return true
    }

    fun undo() {
        if (shapes.isNotEmpty()) {
            val lastShape = shapes.removeLast()
            redoShapes.add(lastShape)
            invalidate()
        }
    }

    fun redo() {
        if (redoShapes.isNotEmpty()) {
            val lastRedoShape = redoShapes.removeLast()
            shapes.add(lastRedoShape)
            invalidate()
        }
    }

    fun clearAll() {
        shapes.clear()
        redoShapes.clear()
        invalidate()
    }

    private fun BrushType.toShape(
        x: Float,
        y: Float,
        color: Int,
        strokeWidth: Float,
    ): BrushShape {
        val paint = createPaint(color, strokeWidth)
        return when (this) {
            BrushType.PEN -> Pencil(x, y, paint, strokeWidth)
            BrushType.RECTANGLE -> Rectangle(x, y, paint, strokeWidth)
            BrushType.CIRCLE -> Circle(x, y, paint, strokeWidth)
            BrushType.ERASER -> Eraser(x, y, paint, strokeWidth)
        }
    }

    private fun BrushShape.copy(
        x: Float = this.startX,
        y: Float = this.startY,
        paint: Paint = this.paint,
        strokeWidth: Float = this.strokeWidth,
    ): BrushShape {
        return when (this) {
            is Pencil -> Pencil(x, y, Paint(paint), strokeWidth)
            is Rectangle -> Rectangle(x, y, Paint(paint), strokeWidth)
            is Circle -> Circle(x, y, Paint(paint), strokeWidth)
            is Eraser -> Eraser(x, y, Paint(paint), strokeWidth)
        }
    }

    companion object {
        private const val DEFAULT_COLOR = Color.BLACK
        private val DEFAULT_STROKE_WIDTH = 5f.dp

        @BindingAdapter("brushType")
        @JvmStatic
        fun DrawingPaperView.bindBrushType(brushType: BrushType) {
            currentShape = brushType.toShape(
                currentShape.startX,
                currentShape.startY,
                currentColor,
                currentStrokeWidth
            )
        }
    }
}
