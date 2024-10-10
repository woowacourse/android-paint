package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class EraserShape(private val path: Path) : Shape {
    override fun draw(
        canvas: Canvas,
        paint: Paint,
    ) {
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path, paint)
    }

    override fun update(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }
}
