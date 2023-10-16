package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.BrushCareTaker
import woowacourse.paint.model.BrushMemento
import woowacourse.paint.model.BrushPen
import woowacourse.paint.model.Brushes

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var brushCareTaker: BrushCareTaker = BrushCareTaker()
        set(value) {
            brushes = value.currentMemento.brushes
            field = value
            updatePaper()
        }

    private var brushes = Brushes()

    private var brush: Brush? = null

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

    var brushGenerator: () -> Brush = ::BrushPen

    var onUndoHistoryChangeListener: (Boolean) -> Unit = {}

    var onRedoHistoryChangeListener: (Boolean) -> Unit = {}

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        brushes.drawOn(canvas)
        brush?.drawOn(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> onActionDown(event)
        MotionEvent.ACTION_MOVE -> onActionMove(event)
        MotionEvent.ACTION_UP -> onActionUp(event)
        else -> super.onTouchEvent(event)
    }

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState())
            putParcelable(KEY_BRUSH_CARE_TAKER, brushCareTaker)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            brushCareTaker = state.getParcelable(KEY_BRUSH_CARE_TAKER)!!
            super.onRestoreInstanceState(state.getParcelable(KEY_SUPER_STATE))
        }
    }

    private fun onActionDown(event: MotionEvent): Boolean {
        brush = brushGenerator().apply {
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

    companion object {
        private const val KEY_SUPER_STATE = "KEY_SUPER_STATE"
        private const val KEY_BRUSH_CARE_TAKER = "KEY_BRUSH_CARE_TAKER"
    }
}
