package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class PenPainting(
    private val path: Path = Path(),
) : Painting {

    override fun movePath(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
    }

    override fun initPath(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun newPainting(): Painting {
        return PenPainting()
    }
}
