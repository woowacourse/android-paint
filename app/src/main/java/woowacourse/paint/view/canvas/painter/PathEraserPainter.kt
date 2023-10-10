package woowacourse.paint.view.canvas.painter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import woowacourse.paint.common.softPainter
import woowacourse.paint.view.palette.color.PaletteColor

data class PathEraserPainter(private val thickness: Float) : Painter(
    Paint().softPainter(thickness = thickness, porterDuffMode = PorterDuff.Mode.CLEAR),
) {
    private val path: Path = Path()
    private var prevX: Float = 0F
    private var prevY: Float = 0F

    override fun setPaletteColor(paletteColor: PaletteColor): Painter = copy()

    override fun setThickness(thickness: Float): PathEraserPainter = copy(
        thickness = thickness,
    )

    override fun onActionDown(x: Float, y: Float) {
        eraseDotTo(x, y)
    }

    private fun eraseDotTo(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
        updatePrevPoint(x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        eraseLine(x, y)
    }

    private fun eraseLine(x: Float, y: Float) {
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

    override fun extract(): Painter = copy()
}
