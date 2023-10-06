package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.util.createRectFOf

class BrushRect(paint: Paint) : BrushFigure(paint) {
    override fun addFigure(point: Point) {
        path.addRect(createRectFOf(basePoint, point), Path.Direction.CCW)
    }
}
