package woowacourse.paint.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.CirclePainting
import woowacourse.paint.model.EraserPainting
import woowacourse.paint.model.PaintBrush
import woowacourse.paint.model.Painting
import woowacourse.paint.model.PaintingHistory
import woowacourse.paint.model.PenPainting
import woowacourse.paint.model.RectanglePainting

class PaintingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    var canvasCallback: CanvasCallback? = null
    lateinit var history: PaintingHistory
    private var painting: Painting = PenPainting()
    private var previousX = 0f
    private var previousY = 0f

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
                painting = painting.movePath(pointX, pointY)
                previousX = pointX
                previousY = pointY
            }

            MotionEvent.ACTION_MOVE -> {
                painting.initPath(previousX, previousY, pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                canvasCallback?.onActionUp(painting)
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    fun setStroke(value: Float) {
        if (painting is PenPainting || painting is EraserPainting) {
            painting = painting.setStroke(value)
        }
    }

    fun setColor(color: Int) {
        if (painting is PenPainting || painting is RectanglePainting || painting is CirclePainting) {
            painting = painting.setColor(context.getColor(color))
        }
    }

    fun setBrush(brush: PaintBrush) {
        painting = painting.setPaintBrush(brush.brushTool)
    }

    fun undoCanvas() {
        canvasCallback?.onUndoHistory()
        painting = painting.getNewPainting()
        invalidate()
    }

    fun redoCanvas() {
        canvasCallback?.onRedoHistory()
        painting = painting.getNewPainting()
        invalidate()
    }

    fun resetCanvas() {
        canvasCallback?.onClearHistory()
        painting = painting.getNewPainting()
        invalidate()
    }

    companion object {
        const val DEFAULT_STROKE_WIDTH = 50.0f
    }
}
