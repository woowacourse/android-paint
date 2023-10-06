package woowacourse.paint.model

import woowacourse.paint.util.createRectFOf

class BrushCircle : BrushFigure() {
    override fun addFigure(point: Point) {
        path.addArc(createRectFOf(basePoint, point), 0F, 360F)
    }
}
