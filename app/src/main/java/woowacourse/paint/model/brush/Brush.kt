package woowacourse.paint.model.brush

import woowacourse.paint.model.drawing.PathPoint

interface Brush {
    fun startDrawing(point: PathPoint)
    fun moveDrawing(point: PathPoint)
}
