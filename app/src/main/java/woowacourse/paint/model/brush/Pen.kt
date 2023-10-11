package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.customview.FreeDrawView

class Pen(private val paintInstance: BrushPaint = BrushPaint()) : Brush(paintInstance) {
    override fun updateStyle(paint: Paint) {
        paintInstance.setPenBrush(paint)
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

    private fun startDraw(x: Float, y: Float) {
        val path = Path().apply { moveTo(x, y) }
        FreeDrawView.previousDrawings.add(path to paintInstance)
    }

    private fun drawLine(x: Float, y: Float) {
        FreeDrawView.previousDrawings.last().first.lineTo(x, y)
    }
}
