package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Pen(override val paintInstance: Paint = Paint()) : Brush() {
    override fun updateStyle(paint: Paint) {
        paintInstance.apply {
            set(paint)
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    override fun onActionDown(xCursor: Float, yCursor: Float) {
        startDraw(xCursor, yCursor)
    }

    override fun onActionMove(xCursor: Float, yCursor: Float) {
        drawLine(xCursor, yCursor)
    }

    override fun onActionUp(xCursor: Float, yCursor: Float) = Unit
    override fun updateColor(color: Int) {
        paintInstance.color = color
    }

    override fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    override fun copyPaint(): Paint = Paint().apply { set(paintInstance) }
    private fun startDraw(x: Float, y: Float) {
        val path = Path().apply { moveTo(x, y) }
        val paint = Paint().apply {
            set(paintInstance)
        }
        previousDrawings.add(path to paint)
    }

    private fun drawLine(x: Float, y: Float) {
        previousDrawings.last().first.lineTo(x, y)
    }
}
