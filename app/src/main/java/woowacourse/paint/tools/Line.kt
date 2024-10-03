package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Line(val path: Path = Path(), val paint: Paint = createDefaultPaint()) : DrawingTool {
    override fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    ) {
        paint.color = newPaint.color
        paint.strokeWidth = newPaint.strokeWidth
        path.moveTo(x, y)
    }

    override fun draw(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    override fun renderOnCanvas(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    private companion object {
        fun createDefaultPaint(): Paint {
            return Paint().apply {
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
            }
        }
    }
}
