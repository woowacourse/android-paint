package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class LineDrawingEngine(
    override val paint: Paint = Paint(),
    override val path: Path = Path(),
) : PathDrawingEngine {

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun move(pointX: Float, pointY: Float) {
        quadTo(pointX, pointY)
    }

    override fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (DrawingEngines.lastX + pointX) / 2
        val nextY = (DrawingEngines.lastY + pointY) / 2
        path.quadTo(DrawingEngines.lastX, DrawingEngines.lastY, nextX, nextY)
        DrawingEngines.updateLastPoint(pointX, pointY)
    }

    override fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
