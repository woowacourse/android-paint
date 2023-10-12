package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path

abstract class BrushLine : Brush {
    override val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        pathEffect = CornerPathEffect(100F)
    }

    private val path = Path()

    private var lastPoint = Point(0F, 0F)

    override fun startDrawing(x: Float, y: Float, onSuccess: () -> Unit) =
        startDrawing(Point(x, y), onSuccess)

    private fun startDrawing(point: Point, onSuccess: () -> Unit) {
        path.moveTo(point.x + ADJUSTMENT, point.y + ADJUSTMENT)
        path.lineTo(point.x, point.y)
        lastPoint = point
        onSuccess()
    }

    override fun continueDrawing(x: Float, y: Float, onSuccess: () -> Unit) =
        continueDrawing(Point(x, y), onSuccess)

    private fun continueDrawing(point: Point, onSuccess: () -> Unit = {}) {
        if (isDrawable(point)) {
            path.lineTo(point.x, point.y)
            lastPoint = point
            onSuccess()
        }
    }

    private fun isDrawable(point: Point) = point.distanceTo(lastPoint) > THRESHOLD

    override fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    companion object {
        private const val ADJUSTMENT = Float.MIN_VALUE
        private const val THRESHOLD = 5
    }
}
