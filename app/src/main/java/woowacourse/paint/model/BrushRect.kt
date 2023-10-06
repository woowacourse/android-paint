package woowacourse.paint.model

import android.graphics.Path
import woowacourse.paint.util.createRectFOf

class BrushRect : BrushFigure() {
    override fun addFigure(point: Point) {
        path.addRect(createRectFOf(basePoint, point), Path.Direction.CCW)
    }
}
