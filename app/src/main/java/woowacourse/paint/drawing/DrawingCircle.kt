package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.ui.PathPoint
import kotlin.math.max
import kotlin.math.min

class DrawingCircle : DrawingShape {
    private var startPoint: PathPoint = PathPoint(0f, 0f)
    private var endPoint: PathPoint = PathPoint(0f, 0f)

    override fun drawShapeOnCanvas(canvas: Canvas, paint: Paint) {
        processDirectionalLogic { startX, startY, radius ->
            canvas.drawCircle(
                startX, startY, radius, paint
            )
        }
    }

    override fun addShapeToPath(path: Path) {
        processDirectionalLogic { startX, startY, radius ->
            path.addCircle(
                startX, startY, radius, Path.Direction.CW
            )
        }
    }

    fun initPoint(value: PathPoint) {
        startPoint = value
        endPoint = value
    }

    fun updateEndPoint(point: PathPoint) {
        endPoint = point
    }

    private fun processDirectionalLogic(
        logic: (startX: Float, startY: Float, radius: Float) -> Unit,
    ) {
        val startX = (startPoint.x + endPoint.x) / 2
        val startY = (startPoint.y + endPoint.y) / 2
        val maxX = max(startPoint.x, endPoint.x)
        val minX = min(startPoint.x, endPoint.x)
        val radius = maxX - minX
        logic(startX, startY, radius)
    }
}
