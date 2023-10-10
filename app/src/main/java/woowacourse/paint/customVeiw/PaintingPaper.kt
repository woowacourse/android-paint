package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.BrushHistory
import woowacourse.paint.model.BrushPen
import woowacourse.paint.model.BrushShape

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var brushHistory: BrushHistory = BrushHistory()

    private var brush: Brush? = null

    private val previewBrush: Brush
        get() = BrushPen().apply {
            setUpPaint(paint)
            start(100F, 100F)
            move(200F, 100F)
        }

    var brushColor = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }

    var brushSize = 10F
        set(value) {
            field = value
            invalidate()
        }

    var brushShape = BrushShape.LINE

    var onUndoHistoryChangeListener: (Boolean) -> Unit = {}

    var onRedoHistoryChangeListener: (Boolean) -> Unit = {}

    init {
        background = ColorDrawable(Color.WHITE)
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        brushHistory.drawOn(canvas)
        brush?.drawOn(canvas)
        previewBrush.drawOn(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> onActionDown(event)
        MotionEvent.ACTION_MOVE -> onActionMove(event)
        MotionEvent.ACTION_UP -> onActionUp(event)
        else -> super.onTouchEvent(event)
    }

    private fun onActionDown(event: MotionEvent): Boolean {
        brush = Brush.from(brushShape).apply {
            setUpPaint(paint)
            start(event.x, event.y) { updatePaper() }
        }
        return true
    }

    private fun onActionMove(event: MotionEvent): Boolean {
        brush?.move(event.x, event.y) { updatePaper() }
        return true
    }

    private fun onActionUp(event: MotionEvent): Boolean {
        brush?.let { brushHistory += it }
        brush = null
        updatePaper()
        return true
    }

    fun undo() {
        brushHistory.undo { updatePaper() }
    }

    fun redo() {
        brushHistory.redo { updatePaper() }
    }

    fun clear() {
        brushHistory.clear { updatePaper() }
    }

    private fun setUpPaint(paint: Paint) {
        paint.apply {
            color = this@PaintingPaper.brushColor
            strokeWidth = brushSize
        }
    }

    private fun updatePaper() {
        onUndoHistoryChangeListener(brushHistory.hasHistory)
        onRedoHistoryChangeListener(brushHistory.hasUndoHistory)
        invalidate()
    }
}
