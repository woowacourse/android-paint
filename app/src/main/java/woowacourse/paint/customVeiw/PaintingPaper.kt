package woowacourse.paint.customVeiw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Math.sqrt

class PaintingPaper constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val brushes = mutableListOf<Brush>()

    private val path
        get() = Path()

    private val paint
        get() = Paint().apply {
            color = this@PaintingPaper.color
            strokeWidth = brushSize
            style = Paint.Style.STROKE
        }

    private var prevPoint = Pair(0f, 0f)

    private val previewBrush
        get() = Brush(
            Path().apply {
                moveTo(100F, 100F)
                lineTo(200F, 100F)
            },
            paint,
        )

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
        brushes.forEach {
            canvas.drawPath(it.path, it.paint)
        }
        canvas.drawPath(previewBrush.path, previewBrush.paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean = when (event.action) {
        MotionEvent.ACTION_DOWN -> {
            brushes += Brush(path.apply { moveTo(event.x, event.y) }, paint)
            true
        }

        MotionEvent.ACTION_MOVE -> {
            if (calculateDistance(event, prevPoint) >= 0.1) {
                prevPoint = Pair(event.x, event.y)
                brushes.last().path.lineTo(event.x, event.y)
                invalidate()
            }
            true
        }

        else -> super.onTouchEvent(event)
    }

    private fun calculateDistance(event: MotionEvent, point: Pair<Float, Float>): Float {
        val dx = event.x - point.first
        val dy = event.y - point.second
        return sqrt((dx * dx + dy * dy).toDouble()).toFloat()
    }

    fun undo() {
        if (brushes.isNotEmpty()) {
            brushes.removeLast()
            invalidate()
        }
    }

    fun clear() {
        brushes.clear()
        invalidate()
    }
}
