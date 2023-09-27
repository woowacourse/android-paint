package woowacourse.paint.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class PathPainter(
    val path: Path = Path(),
    val paint: Paint = Paint().softPainter(),
) {
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

    fun dotTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
        lineTo(pointX, pointY)
    }

    fun lineTo(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
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
