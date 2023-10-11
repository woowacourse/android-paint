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
import woowacourse.paint.model.BrushCareTaker
import woowacourse.paint.model.BrushMemento
import woowacourse.paint.model.BrushPen
import woowacourse.paint.model.BrushShape
import woowacourse.paint.model.Brushes

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var brushCareTaker = BrushCareTaker()
        set(value) {
            brushes = value.currentMemento.brushes
            field = value
            updatePaper()
        }

    private var brushes = Brushes()

    private var brush: Brush? = null

    private val previewBrush: Brush
        get() = BrushPen().apply {
            setUpPaint(paint)
            startDrawing(100F, 100F)
            continueDrawing(200F, 100F)
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
        brushes.drawOn(canvas)
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
            startDrawing(event.x, event.y) { updatePaper() }
        }
        return true
    }

    private fun onActionMove(event: MotionEvent): Boolean {
        brush?.continueDrawing(event.x, event.y) { updatePaper() }
        return true
    }

    private fun onActionUp(event: MotionEvent): Boolean {
        brush?.let {
            brushes += it
            brushCareTaker.save(BrushMemento(brushes))
        }
        brush = null
        updatePaper()
        return true
    }

    fun undo() {
        brushCareTaker.undo {
            brushes = it
            updatePaper()
        }
    }

    fun redo() {
        brushCareTaker.redo {
            brushes = it
            updatePaper()
        }
    }

    fun clear() {
        brushes = Brushes()
        brushCareTaker.save(BrushMemento(brushes))
        updatePaper()
    }

    private fun setUpPaint(paint: Paint) {
        paint.apply {
            color = this@PaintingPaper.brushColor
            strokeWidth = brushSize
        }
    }

    private fun updatePaper() {
        onUndoHistoryChangeListener(brushCareTaker.hasHistory)
        onRedoHistoryChangeListener(brushCareTaker.hasUndoHistory)
        invalidate()
    }
}
