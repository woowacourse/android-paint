package woowacourse.paint.model.brush

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.drawing.Drawing
import woowacourse.paint.model.drawing.DrawingHistory
import woowacourse.paint.model.drawing.PathPoint
import kotlin.math.max
import kotlin.math.min

class Rectangle(private var paint: Paint) : PathBrush {
    private var startPoint: PathPoint = PathPoint(0f, 0f)
    private var endPoint: PathPoint = PathPoint(0f, 0f)
    private var path: Path = Path()

    private fun addShape() {
        val left = min(startPoint.x, endPoint.x)
        val right = max(startPoint.x, endPoint.x)
        val top = min(startPoint.y, endPoint.y)
        val bottom = max(startPoint.y, endPoint.y)
        path.addRect(left, top, right, bottom, Path.Direction.CCW)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint)
    }

    override fun startDrawing(point: PathPoint) {
        startPoint = point
        endPoint = point
    }

    override fun moveDrawing(point: PathPoint) {
        endPoint = point
    }

    override fun endDrawing(drawingHistory: DrawingHistory) {
        addShape()
        drawingHistory.addDrawing(Drawing(path, paint))
        this.path = Path()
    }

    override fun changePaint(paint: Paint) {
        this.paint = paint
    }
}
