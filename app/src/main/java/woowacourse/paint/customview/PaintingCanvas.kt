package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.BrushBox
import woowacourse.paint.model.Painting
import woowacourse.paint.model.PaintingHistory

class PaintingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    var canvasCallback: CanvasCallback? = null
    lateinit var history: PaintingHistory
    private var painting: Painting = Painting()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        history.forEach {
            it.draw(canvas)
        }
        painting.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                painting.movePath(pointX, pointY)
            }

            MotionEvent.ACTION_MOVE -> {
                painting.initPath(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                canvasCallback?.onActionUp(painting)
                painting = painting.newDrawingPainting()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStroke(value: Float) {
        painting.setStrokeWidth(value)
    }

    fun setColor(color: Int) {
        painting.setColor(context.getColor(color))
    }

    fun setBrush(brush: BrushBox) {
        painting = painting.setPainting(brush.brushTool)
    }

    fun undoCanvas() {
        canvasCallback?.onUndoHistory()
        invalidate()
    }

    fun redoCanvas() {
        canvasCallback?.onRedoHistory()
        invalidate()
    }

    fun resetCanvas() {
        canvasCallback?.onClearHistory()
        invalidate()
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
