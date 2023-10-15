package woowacourse.paint.model.drawingEngine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

abstract class PathDrawingEngine : DrawingEngine {

    abstract override val paint: Paint
    abstract val path: Path

    private var lastX: Float = 0f
    private var lastY: Float = 0f

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setEndPoint(pointX: Float, pointY: Float) {
        quadTo(lastX, lastY, pointX, pointY)
        lastX = pointX
        lastY = pointY
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    private fun quadTo(fromX: Float, fromY: Float, toX: Float, toY: Float) {
        val nextX = (fromX + toX) / 2
        val nextY = (fromY + toY) / 2
        path.quadTo(fromX, fromY, nextX, nextY)
    }

    protected fun setLastPoint(pointX: Float, pointY: Float) {
        lastX = pointX
        lastY = pointY
    }
}
