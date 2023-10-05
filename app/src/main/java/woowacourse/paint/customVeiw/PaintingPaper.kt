package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Xfermode
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.BrushCircle
import woowacourse.paint.model.BrushPen
import woowacourse.paint.model.BrushRect
import woowacourse.paint.model.Brushes
import woowacourse.paint.model.Shape

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val brushes: Brushes = Brushes()

    private val previewBrush
        get() = BrushPen(
            Path().apply {
                moveTo(100F, 100F)
                lineTo(200F, 100F)
            },
            penPaint,
        )

    private val path
        get() = Path()

    private val paint
        get() = Paint().apply { color = this@PaintingPaper.color }

    private val penPaint
        get() = paint.apply {
            strokeWidth = brushSize
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            xfermode = _xfermode
            pathEffect = CornerPathEffect(100F)
        }

    private var _xfermode: Xfermode? = null

    var color = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }

    var brushSize = 10F
        set(value) {
            field = value
            invalidate()
        }

    var shape = Shape.LINE

    init {
        background = ColorDrawable(Color.WHITE)
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    var onUndoHistoryChangeListener: (Boolean) -> Unit = {}

    var onRedoHistoryChangeListener: (Boolean) -> Unit = {}

    var onEraseModeChangeListener: (Boolean) -> Unit = {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        brushes.drawOn(canvas)
        previewBrush.drawOn(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> onActionDown(event)
        MotionEvent.ACTION_MOVE -> onActionMove(event)
        else -> super.onTouchEvent(event)
    }

    private fun onActionDown(event: MotionEvent): Boolean {
        brushes += when (shape) {
            Shape.LINE -> createBrushPen(event)
            Shape.RECT -> createBrushRect(event)
            Shape.CIRCLE -> createBrushCircle(event)
        }
        return true
    }

    private fun createBrushPen(event: MotionEvent): Brush {
        return BrushPen(path, penPaint).apply { start(event.x, event.y, ::updatePaper) }
    }

    private fun createBrushRect(event: MotionEvent): Brush {
        return BrushRect(path, paint).apply { start(event.x, event.y, ::updatePaper) }
    }

    private fun createBrushCircle(event: MotionEvent): Brush {
        return BrushCircle(path, paint).apply { start(event.x, event.y, ::updatePaper) }
    }

    private fun onActionMove(event: MotionEvent): Boolean {
        brushes.last().move(event.x, event.y, ::updatePaper)
        return true
    }

    fun undo() {
        brushes.undo(::updatePaper)
    }

    fun redo() {
        brushes.redo(::updatePaper)
    }

    fun clear() {
        brushes.clear(::updatePaper)
    }

    private fun updatePaper() {
        onUndoHistoryChangeListener(brushes.hasHistory)
        onRedoHistoryChangeListener(brushes.hasUndoHistory)
        invalidate()
    }

    fun drawMode() {
        _xfermode = null
        onEraseModeChangeListener(false)
    }

    fun eraseMode() {
        _xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        shape = Shape.LINE
        onEraseModeChangeListener(true)
    }
}
