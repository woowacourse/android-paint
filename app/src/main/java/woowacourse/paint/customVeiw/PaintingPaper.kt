package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.model.Brush
import woowacourse.paint.model.Brushes

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val brushes: Brushes = Brushes()

    private val previewBrush
        get() = Brush(
            Path().apply {
                moveTo(100F, 100F)
                lineTo(200F, 100F)
            },
            paint,
        )

    private val path
        get() = Path()

    private val paint
        get() = Paint().apply {
            color = this@PaintingPaper.color
            strokeWidth = brushSize
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(100F)
        }

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

    init {
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        brushes.drawOn(canvas)
        previewBrush.drawOn(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            brushes += Brush(path, paint).apply { start(event.x, event.y) }
            invalidate()
            true
        }

        MotionEvent.ACTION_MOVE -> {
            if (brushes.last().move(event.x, event.y)) {
                invalidate()
            }
            true
        }

        else -> super.onTouchEvent(event)
    }

    fun undo() {
        brushes.undo()
        invalidate()
    }

    fun redo() {
        brushes.redo()
        invalidate()
    }

    fun clear() {
        brushes.clear()
        invalidate()
    }
}
