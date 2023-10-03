package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

class Painting(
    val path: Path,
    val paint: Paint,
) {
    fun startDraw(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
        path.lineTo(pointX, pointY)
    }

    fun onDraw(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun changeSize(value: Float) {
        paint.strokeWidth = value
    }

    fun changeColor(value: Int) {
        paint.color = value
    }
}
