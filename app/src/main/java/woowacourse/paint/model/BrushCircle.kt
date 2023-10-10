package woowacourse.paint.model

import android.graphics.Path
import woowacourse.paint.util.createRectFOf

class BrushCircle : BrushFigure() {
    override fun addFigure(point: Point) {
        path.addOval(createRectFOf(basePoint, point), Path.Direction.CW)
    }
}
