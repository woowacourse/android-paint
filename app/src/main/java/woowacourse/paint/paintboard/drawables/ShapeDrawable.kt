package woowacourse.paint.paintboard.drawables

import android.graphics.Paint
import android.view.MotionEvent
import woowacourse.paint.entity.Point
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.util.point

abstract class ShapeDrawable(
    protected val rect: Rect,
    brush: Brush.ShapeBrush,
) : BrushDrawable {
    protected val paint = Paint().apply {
        style = brush.fillType
        color = brush.color
        strokeWidth = brush.width.toFloat()
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> drawing(event.point)
            MotionEvent.ACTION_UP -> drawing(event.point)
        }
    }

    private fun drawing(point: Point) {
        rect.update(point)
    }
}
