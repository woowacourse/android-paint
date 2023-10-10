package woowacourse.paint.tool

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Pen(private val path: Path = Path()) : Tool {

    override fun copy(): Pen {
        return Pen(Path())
    }

    override fun startDraw(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
        path.lineTo(pointX, pointY)
    }

    override fun onDraw(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    override fun drawPath(canvas: Canvas, paint: Paint) {
        canvas.drawPath(path, paint)
    }
}
