package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Painting(
    private val path: Path,
    private val paintColor: Int,
    private val paintWidth: Float,
) {
    private val paint = Paint().apply {
        strokeWidth = paintWidth
        color = paintColor
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    fun getInitializedPathPainting(): Painting {
        return copy(
            path = Path(),
            paintColor = paint.color,
            paintWidth = paint.strokeWidth,
        )
    }

    fun movePath(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun linePath(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun changeColor(color: Int) {
        paint.color = color
    }

    fun changeWidth(width: Float) {
        paint.strokeWidth = width
    }

    fun drawOnCanvas(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
