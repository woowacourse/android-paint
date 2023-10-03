package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

class PathPaint(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {
    fun setPaintColor(@ColorInt color: Int) {
        paint.color = color
    }

    fun setPaintStrokeSize(size: Float) {
        paint.strokeWidth = size
    }

    fun resetPaint(): PathPaint = PathPaint(paint = Paint(paint))

    fun moveToPath(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun lineToPath(x: Float, y: Float) {
        path.lineTo(x, y)
    }
}
