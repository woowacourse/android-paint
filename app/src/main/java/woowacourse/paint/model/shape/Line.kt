package woowacourse.paint.model.shape

import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path,
    val paint: Paint,
) : Shape {
    constructor(paint: Paint) : this(Path(), paint)
    constructor() : this(Path(), Paint())

    fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (Shapes.lastX + pointX) / 2
        val nextY = (Shapes.lastY + pointY) / 2
        path.quadTo(Shapes.lastX, Shapes.lastY, nextX, nextY)
        Shapes.updateLastPoint(pointX, pointY)
    }
}
