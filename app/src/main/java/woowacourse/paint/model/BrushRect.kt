package woowacourse.paint.model

import android.graphics.Path

class BrushRect : BrushFigure() {
    override fun addFigure(point: Point) {
        path.addRect(basePoint.getRectF(point), Path.Direction.CCW)
    }
}
