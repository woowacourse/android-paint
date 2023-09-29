package woowacourse.paint.presentation.ui.canvas

import android.graphics.Canvas
import android.graphics.Path

class Line(
    private val path: Path = Path(),
    val palette: Palette = Palette(),
) {

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, palette.paint)
    }

    fun lineTo(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
