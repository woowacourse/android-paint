package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.ui.PathPoint

class DrawingCircle : DrawingShape {
    private var startPoint: PathPoint = PathPoint(0f, 0f)
    private var endPoint: PathPoint = PathPoint(0f, 0f)

    override fun drawShape(canvas: Canvas, paint: Paint) {
        processDirectionalLogic { startX, startY, radius ->
            canvas.drawCircle(
                startX, startY, radius, paint
            )
        }
    }

    override fun addShape(path: Path) {
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
        if (startPoint.x < endPoint.x) {
            val radius = endPoint.x - startPoint.x
            logic(startPoint.x + radius, startPoint.y + radius, radius)
        } else {
            val radius = startPoint.x - endPoint.x
            logic(startPoint.x - radius, startPoint.y - radius, radius)
        }
    }
}
