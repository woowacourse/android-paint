package woowacourse.paint.view.model.pen

import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path

class LinePen(val onAddLine: (Path, Paint) -> Unit) : Pen {
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        pathEffect = CornerPathEffect(10F)
    }
    private var path: Path = Path()
    override fun startPaint(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
        path.lineTo(pointX, pointY)
    }

    override fun movePaint(pointX: Float, pointY: Float) {
        path.lineTo(pointX, pointY)
    }

    override fun cacheCurrentPaint() {
        onAddLine(path, paint)
        path = Path()
        paint = Paint(paint)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setStrokeWidth(strokeWidth: Float) {
        this.paint.strokeWidth = strokeWidth
    }

    override fun setColor(color: Int) {
        this.paint.color = color
    }
}
