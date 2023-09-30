package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

data class DrawingElement(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {

    fun movePath(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun initPath(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun setStroke(value: Float) = this.copy(path = Path(), paint = paint).apply {
        this.paint.strokeWidth = value
    }

    fun setColor(color: Int) = this.copy(path = Path(), paint = paint).apply {
        this.paint.color = color
    }
}
