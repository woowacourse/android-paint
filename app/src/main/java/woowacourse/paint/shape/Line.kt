package woowacourse.paint.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class Line(private val path: Path = Path()) : Shape {

    override fun copy(): Line {
        return Line()
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
