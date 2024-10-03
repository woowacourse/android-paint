package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path

class Line(val path: Path, val paint: Paint) {
    init {
        setupPaint()
    }

    fun moveTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.moveTo(pointX, pointY)
    }

    fun lineTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    fun updateStrokeWidth(strokeWidth: Float): Line {
        val paint = Paint(paint)
        paint.strokeWidth = strokeWidth
        return Line(Path(), paint)
    }

    fun updateColor(color: Int): Line {
        val paint = Paint(paint)
        paint.color = color
        return Line(Path(), paint)
    }

    private fun setupPaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
}
