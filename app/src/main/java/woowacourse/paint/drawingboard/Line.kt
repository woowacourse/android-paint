package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path

// TODO: Line 네이밍 변경하기
class Line(val path: Path = Path(), val paint: Paint = Paint()) {
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

    fun updateRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.rewind()
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }

    fun addRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.addRect(left, top, right, bottom, Path.Direction.CW)
    }

    fun updateCircle(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.rewind()
        path.addOval(left, top, right, bottom, Path.Direction.CW)
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

    fun updatePaintStyle(style: Paint.Style): Line {
        val paint = Paint(paint)
        paint.style = style
        return Line(Path(), paint)
    }

    private fun setupPaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
}
