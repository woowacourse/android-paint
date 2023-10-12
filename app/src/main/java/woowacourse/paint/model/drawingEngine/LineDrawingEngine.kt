package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class LineDrawingEngine(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) : DrawingEngine {

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun move(pointX: Float, pointY: Float) {
        quadTo(pointX, pointY)
    }

    fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (DrawingEngines.lastX + pointX) / 2
        val nextY = (DrawingEngines.lastY + pointY) / 2
        path.quadTo(DrawingEngines.lastX, DrawingEngines.lastY, nextX, nextY)
        DrawingEngines.updateLastPoint(pointX, pointY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }
}
