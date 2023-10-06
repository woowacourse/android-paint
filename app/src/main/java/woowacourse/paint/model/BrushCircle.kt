package woowacourse.paint.model

import android.graphics.Paint
import woowacourse.paint.util.createRectFOf

class BrushCircle(paint: Paint) : BrushFigure(paint) {
    override fun addFigure(point: Point) {
        path.addArc(createRectFOf(basePoint, point), 0F, 360F)
    }
}
