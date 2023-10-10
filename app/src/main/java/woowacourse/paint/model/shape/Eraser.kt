package woowacourse.paint.model.shape

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

data class Eraser(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) : Shape {

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (Shapes.lastX + pointX) / 2
        val nextY = (Shapes.lastY + pointY) / 2
        path.quadTo(Shapes.lastX, Shapes.lastY, nextX, nextY)
        Shapes.updateLastPoint(pointX, pointY)
    }

    fun addRect(width: Float, height: Float) {
        path.addRect(0f, 0f, width, height, Path.Direction.CW)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
