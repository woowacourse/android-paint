package woowacourse.paint.paintboard.drawables

import android.graphics.Canvas
import android.graphics.RectF
import woowacourse.paint.entity.Point
import woowacourse.paint.paintboard.common.Brush

class RectDrawable(
    initRect: Rect,
    brush: Brush.ShapeBrush,
) : ShapeDrawable(initRect, brush) {
    override fun draw(canvas: Canvas) {
        canvas.drawRect(RectF(rect.left, rect.top, rect.right, rect.bottom), paint)
    }

    override fun contain(point: Point): Boolean {
        return (point.x in rect.left..rect.right) && (point.y in rect.top..rect.bottom)
    }
}
