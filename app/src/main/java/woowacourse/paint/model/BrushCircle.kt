package woowacourse.paint.model

import android.graphics.Path

class BrushCircle : BrushFigure() {
    override fun addFigure(point: Point) {
        path.addOval(basePoint.getRectF(point), Path.Direction.CW)
    }
}
