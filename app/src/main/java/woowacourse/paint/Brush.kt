package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Brush(private val path: Path = Path(), val paint: Paint = Paint()) {
    init {
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
    }

    fun moveTo(
        x: Float,
        y: Float,
    ) {
        path.moveTo(x, y)
    }

    fun lineTo(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setThickness(size: Float) {
        paint.strokeWidth = size
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
