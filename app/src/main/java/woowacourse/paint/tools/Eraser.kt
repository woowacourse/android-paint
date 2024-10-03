package woowacourse.paint.tools

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

data class Eraser(val path: Path = Path(), val paint: Paint = createDefaultPaint()) : DrawingTool {
    override fun initialize(): DrawingTool = Eraser()

    override fun setStartPoint(
        x: Float,
        y: Float,
        newPaint: Paint,
    ) {
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
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
        }
    }
}
