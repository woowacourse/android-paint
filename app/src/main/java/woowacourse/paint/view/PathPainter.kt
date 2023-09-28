package woowacourse.paint.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class PathPainter(
    val path: Path = Path(),
    val paint: Paint = Paint().softPainter(),
) {
    private var prevX: Float = 0F
    private var prevY: Float = 0F

    fun setPaintColor(paletteColor: PaletteColor): PathPainter = copy(
        path = Path(),
        paint = updatePaint(paintColor = paletteColor.color),
    )

    fun setThickness(thickness: Float): PathPainter = copy(
        path = Path(),
        paint = updatePaint(thickness = thickness),
    )

    private fun updatePaint(
        paintColor: Int = paint.color,
        thickness: Float = paint.strokeWidth,
    ): Paint = Paint().softPainter().apply {
        color = paintColor
        strokeWidth = thickness
    }

    fun dotTo(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
        updatePrevPoint(x, y)
    }

    fun drawLine(x: Float, y: Float) {
        path.quadTo(prevX, prevY, (prevX + x) / 2, (prevY + y) / 2)
        updatePrevPoint(x, y)
    }

    private fun updatePrevPoint(pointX: Float, pointY: Float) {
        prevX = pointX
        prevY = pointY
    }

    fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}

private fun Paint.softPainter(
    paletteColor: PaletteColor = PaletteColor.RED,
): Paint = apply {
    isAntiAlias = true
    style = Paint.Style.STROKE
    strokeJoin = Paint.Join.ROUND
    strokeCap = Paint.Cap.ROUND
    color = paletteColor.color
}
