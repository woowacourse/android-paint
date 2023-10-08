package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.common.softPainter
import woowacourse.paint.view.palette.color.PaletteColor

data class BrushPainter(
    private val path: Path = Path(),
    val paint: Paint = DEFAULT_PAINT,
) : Painter {
    private var prevX: Float = 0F
    private var prevY: Float = 0F

    override fun setPaletteColor(paletteColor: PaletteColor): Painter = BrushPainter(
        path = Path(),
        paint = updatePaint(paintColor = paletteColor.color),
    )

    override fun setThickness(thickness: Float): BrushPainter = BrushPainter(
        path = Path(),
        paint = updatePaint(thickness = thickness),
    )

    private fun updatePaint(
        paintColor: Int = paint.color,
        thickness: Float = paint.strokeWidth,
    ): Paint = DEFAULT_PAINT.apply {
        color = paintColor
        strokeWidth = thickness
    }

    override fun onActionDown(x: Float, y: Float) {
        dotTo(x, y)
    }

    private fun dotTo(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
        updatePrevPoint(x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        drawLine(x, y)
    }

    private fun drawLine(x: Float, y: Float) {
        path.quadTo(prevX, prevY, (prevX + x) / 2, (prevY + y) / 2)
        updatePrevPoint(x, y)
    }

    private fun updatePrevPoint(pointX: Float, pointY: Float) {
        prevX = pointX
        prevY = pointY
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun extract(): Painter = copy(path = Path())

    companion object {
        private val DEFAULT_PAINT = Paint().softPainter()
    }
}
