package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

abstract class PathDrawingEngine : DrawingEngine {

    abstract override val paint: Paint
    abstract val path: Path

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun draw(pointX: Float, pointY: Float) {
        quadTo(pointX, pointY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    fun quadTo(pointX: Float, pointY: Float) {
        val nextX = (DrawingEngines.lastX + pointX) / 2
        val nextY = (DrawingEngines.lastY + pointY) / 2
        path.quadTo(DrawingEngines.lastX, DrawingEngines.lastY, nextX, nextY)
        DrawingEngines.updateLastPoint(pointX, pointY)
    }
}
