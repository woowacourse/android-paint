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

    fun draw(fromX: Float, fromY: Float, toX: Float, toY: Float) {
        quadTo(fromX, fromY, toX, toY)
    }

    fun moveTo(pointX: Float, pointY: Float) {
        path.moveTo(pointX, pointY)
    }

    private fun quadTo(fromX: Float, fromY: Float, toX: Float, toY: Float) {
        val nextX = (fromX + toX) / 2
        val nextY = (fromY + toY) / 2
        path.quadTo(fromX, fromY, nextX, nextY)
    }
}
