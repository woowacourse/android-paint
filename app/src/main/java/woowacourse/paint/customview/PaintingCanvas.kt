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
            }

            MotionEvent.ACTION_MOVE -> {
                painting.initPath(pointX, pointY)
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
        painting = when (painting) {
            is PenPainting -> (painting as PenPainting).setStroke(value)
            is EraserPainting -> (painting as EraserPainting).setStroke(value)
            else -> return
        }
    }

    fun setColor(color: Int) {
        val paintColor = context.getColor(color)
        painting = when (painting) {
            is PenPainting -> (painting as PenPainting).setColor(paintColor)
            is RectanglePainting -> (painting as RectanglePainting).setColor(paintColor)
            is CirclePainting -> (painting as CirclePainting).setColor(paintColor)
            else -> return
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
