package woowacourse.paint.model.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.drawing.PathPoint
import kotlin.math.max
import kotlin.math.min

class Rectangle : Shape {
    private var startPoint: PathPoint = PathPoint(0f, 0f)
    private var endPoint: PathPoint = PathPoint(0f, 0f)

    override fun drawShapeOnCanvas(canvas: Canvas, paint: Paint) {
        canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint)
    }

    override fun addShapeToPath(path: Path) {
        val minX = min(startPoint.x, endPoint.x)
        val maxX = max(startPoint.x, endPoint.x)
        val minY = min(startPoint.y, endPoint.y)
        val maxY = max(startPoint.y, endPoint.y)
        path.addRect(minX, minY, maxX, maxY, Path.Direction.CW)
    }

    fun initPoint(value: PathPoint) {
        startPoint = value
        endPoint = value
    }

    fun updateEndPoint(point: PathPoint) {
        endPoint = point
    }
}
