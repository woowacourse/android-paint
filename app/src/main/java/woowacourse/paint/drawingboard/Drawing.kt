package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path

class Drawing(val path: Path, val paint: Paint) {
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

    fun drawRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.reset()
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }

    fun drawCircle(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.reset()
        path.addOval(left, top, right, bottom, Path.Direction.CW)
    }

    fun updateStrokeWidth(strokeWidth: Float): Drawing {
        val paint = Paint(paint)
        paint.strokeWidth = strokeWidth
        return Drawing(Path(), paint)
    }

    fun updateColor(color: Int): Drawing {
        val paint = Paint(paint)
        paint.color = color
        return Drawing(Path(), paint)
    }

    fun updatePaintStyle(style: Paint.Style): Drawing {
        val paint = Paint(paint)
        paint.style = style
        return Drawing(Path(), paint)
    }

    private fun setupPaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
}
