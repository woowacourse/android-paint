package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

data class Line(val path: Path = Path(), val paint: Paint = createDefaultPaint(), val isEraser: Boolean = false) : DrawingTool {
    override fun initialize(): DrawingTool = if (isEraser) Line(isEraser = true) else Line()

    override fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    ) {
        paint.strokeWidth = newPaint.strokeWidth

        if (isEraser) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            paint.xfermode = null
            paint.color = newPaint.color
        }
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
