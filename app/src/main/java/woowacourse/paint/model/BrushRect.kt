package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.util.createRectFOf

class BrushRect(private val paint: Paint) : Brush {
    private val path = Path()

    private var basePoint = Point(0F, 0F)

    override fun start(x: Float, y: Float, onCommit: () -> Unit) = start(Point(x, y), onCommit)

    private fun start(point: Point, onCommit: () -> Unit) {
        basePoint = point
        onCommit()
    }

    override fun move(x: Float, y: Float, onCommit: () -> Unit) = move(Point(x, y), onCommit)

    private fun move(point: Point, onSuccess: () -> Unit = {}) {
        path.reset()
        path.addRect(createRectFOf(basePoint, point), Path.Direction.CCW)
        onSuccess()
    }

    override fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
