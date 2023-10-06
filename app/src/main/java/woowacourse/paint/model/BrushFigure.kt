package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

abstract class BrushFigure(private val paint: Paint) : Brush {
    protected val path = Path()

    protected var basePoint = Point(0F, 0F)

    override fun start(x: Float, y: Float, onCommit: () -> Unit) = start(Point(x, y), onCommit)

    private fun start(point: Point, onCommit: () -> Unit) {
        basePoint = point
        onCommit()
    }

    override fun move(x: Float, y: Float, onCommit: () -> Unit) = move(Point(x, y), onCommit)

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
