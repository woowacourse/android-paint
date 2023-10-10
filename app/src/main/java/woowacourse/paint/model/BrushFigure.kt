package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

abstract class BrushFigure : Brush {
    override val paint: Paint = Paint()

    protected val path = Path()

    protected var basePoint = Point(0F, 0F)

    override fun startDrawing(x: Float, y: Float, onSuccess: () -> Unit) = start(Point(x, y), onSuccess)

    private fun start(point: Point, onSuccess: () -> Unit) {
        basePoint = point
        onSuccess()
    }

    override fun continueDrawing(x: Float, y: Float, onSuccess: () -> Unit) = move(Point(x, y), onSuccess)

    private fun move(point: Point, onSuccess: () -> Unit = {}) {
        path.reset()
        addFigure(point)
        onSuccess()
    }

    abstract fun addFigure(point: Point)

    override fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
