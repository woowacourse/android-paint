package woowacourse.paint.paintboard.drawables

import android.graphics.Canvas
import android.view.MotionEvent
import woowacourse.paint.entity.Point
import woowacourse.paint.entity.Points
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.common.Shape
import woowacourse.paint.util.point

class BrushDrawables(initBrush: Brush) {
    private val _values = mutableListOf<BrushDrawable>()
    val values get() = _values.toList()

    private val removedValues = mutableListOf<BrushDrawable>()
    var brush: Brush = initBrush

    fun action(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            _values.add(createDrawable(event.point))
            removedValues.clear()
        }
        _values.lastOrNull()?.action(event)
    }

    private fun createDrawable(point: Point): BrushDrawable {
        return when (brush) {
            is Brush.LineBrush -> LineDrawable(Points(listOf(point)), brush as Brush.LineBrush)
            is Brush.EraserBrush ->
                EraserDrawable(Points(listOf(point)), brush as Brush.EraserBrush, ::erase)

            is Brush.ShapeBrush -> {
                when ((brush as Brush.ShapeBrush).mode) {
                    Shape.RECT -> RectDrawable(Rect(point), brush as Brush.ShapeBrush)
                    Shape.OVAL -> OvalDrawable(Rect(point), brush as Brush.ShapeBrush)
                }
            }
        }
    }

    private fun erase(point: Point) {
        _values.forEachIndexed { idx: Int, drawable: BrushDrawable ->
            if (drawable.contain(point)) {
                _values.removeAt(idx).also { removedValues.add(it) }
                return
            }
        }
    }

    fun draw(canvas: Canvas) {
        _values.forEach { it.draw(canvas) }
    }

    fun undo() {
        val item: BrushDrawable? = _values.removeLastOrNull()
        item?.let { removedValues.add(it) }
    }

    fun redo() {
        val item: BrushDrawable? = removedValues.removeLastOrNull()
        item?.let { _values.add(it) }
    }

    fun clear() {
        removedValues.clear()
        _values.clear()
    }
}
