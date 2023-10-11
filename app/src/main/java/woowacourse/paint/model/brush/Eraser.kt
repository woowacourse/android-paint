package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.RectF
import woowacourse.paint.customview.FreeDrawView

class Eraser(private val paintInstance: BrushPaint = BrushPaint()) : Brush(paintInstance) {
    override fun updateStyle(paint: Paint) = Unit

    override fun onActionDown(xCursor: Float, yCursor: Float) {
        erase(xCursor, yCursor)
    }

    override fun onActionMove(xCursor: Float, yCursor: Float) = Unit

    override fun onActionUp(xCursor: Float, yCursor: Float) = Unit
    override fun updateColor(color: Int) = Unit

    override fun updateThickness(thickness: Float) = Unit

    private fun erase(cursorX: Float, cursorY: Float) {
        FreeDrawView.previousDrawings.lastOrNull {
            val bounds = RectF()
            it.first.computeBounds(bounds, false)
            bounds.contains(cursorX, cursorY)
        }?.let {
            FreeDrawView.previousDrawings.remove(it)
        }
    }
}
