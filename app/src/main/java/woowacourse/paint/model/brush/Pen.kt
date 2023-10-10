package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

object Pen : Brush() {
    override fun updateStyle() {
        paintInstance.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        startDraw(xCursor, yCursor)
    }

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        drawLine(xCursor, yCursor)
        updateView()
    }

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: () -> Unit) = Unit

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
