package woowacourse.paint.model.shape

import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) : Shape {

    override fun move(pointX: Float, pointY: Float) {
        quadTo(pointX, pointY)
    }

    fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (Shapes.lastX + pointX) / 2
        val nextY = (Shapes.lastY + pointY) / 2
        path.quadTo(Shapes.lastX, Shapes.lastY, nextX, nextY)
        Shapes.updateLastPoint(pointX, pointY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
