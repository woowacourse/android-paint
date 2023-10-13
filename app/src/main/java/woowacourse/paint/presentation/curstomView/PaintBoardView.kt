package woowacourse.paint.presentation.curstomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.presentation.ui.model.Brush
import woowacourse.paint.presentation.ui.model.GraphicPrimitive
import kotlin.properties.Delegates

class PaintBoardView(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {

    private val history: History = History()
    private var graphicPrimitives: List<GraphicPrimitive> by Delegates.observable(emptyList()) { _, _, _ ->
        invalidate()
    }
    private var brush: Brush = Brush.PEN
    private val paint: Paint = Paint()
    private var downPoint: Pair<Float, Float> = 0f to 0f
    private val touchEventListeners: MutableList<TouchEventListener> = mutableListOf()

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        graphicPrimitives.forEach { canvas.drawPath(it.path, it.paint) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                graphicPrimitives = brush.onActionDown(
                    graphicPrimitives = graphicPrimitives,
                    pointX = pointX,
                    pointY = pointY,
                    paint = paint,
                )
                downPoint = pointX to pointY
            }

            MotionEvent.ACTION_MOVE -> {
                val (basePointX, basePointY) = downPoint
                graphicPrimitives = brush.onActionMove(
                    graphicPrimitives = graphicPrimitives,
                    basePointX = basePointX,
                    basePointY = basePointY,
                    pointX = pointX,
                    pointY = pointY,
                    paint = paint,
                )
            }

            MotionEvent.ACTION_UP -> history.addRecord(graphicPrimitives)

            else -> super.onTouchEvent(event)
        }
        touchEventListeners.forEach { it.onTouch() }
        return true
    }

    fun setBrushColor(colorCode: Int) {
        paint.color = colorCode
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun setBrush(brush: Brush) {
        this.brush = brush
    }

    fun addTouchEventListener(listener: TouchEventListener) {
        touchEventListeners.add(listener)
    }

    fun undo() {
        graphicPrimitives = history.undo()
    }

    fun redo() {
        graphicPrimitives = history.redo()
    }

    fun reset() {
        graphicPrimitives = history.clear()
    }

    fun interface TouchEventListener {
        fun onTouch()
    }

    private class History {
        private val records: MutableList<List<GraphicPrimitive>> = mutableListOf(emptyList())

        private var snapshotIndex = 0

        fun addRecord(record: List<GraphicPrimitive>) {
            records.add(++snapshotIndex, record)
            while (records.size > snapshotIndex + 1) {
                records.removeLastOrNull()
            }
        }

        fun redo(): List<GraphicPrimitive> {
            snapshotIndex = minOf(snapshotIndex + 1, records.size - 1)
            return records[snapshotIndex]
        }

        fun undo(): List<GraphicPrimitive> {
            snapshotIndex = maxOf(snapshotIndex - 1, 0)
            return records[snapshotIndex]
        }

        fun clear(): List<GraphicPrimitive> {
            records.clear()
            records.add(emptyList())
            snapshotIndex = 0
            return records[snapshotIndex]
        }
    }
}
