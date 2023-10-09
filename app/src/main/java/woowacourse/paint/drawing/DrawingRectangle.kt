package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.ui.PathPoint

class DrawingRectangle : DrawingShape {
    private var startPoint: PathPoint = PathPoint(0f, 0f)
    private var endPoint: PathPoint = PathPoint(0f, 0f)

    override fun drawShape(canvas: Canvas, paint: Paint) {
        canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint)
    }

    override fun addShape(path: Path) {
        if (startPoint.x < endPoint.x) {
            path.addRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, Path.Direction.CW)
        } else {
            path.addRect(endPoint.x, endPoint.y, startPoint.x, startPoint.y, Path.Direction.CW)
        }
    }

    fun initPoint(value: PathPoint) {
        startPoint = value
        endPoint = value
    }

    fun updateEndPoint(point: PathPoint) {
        endPoint = point
    }
}
