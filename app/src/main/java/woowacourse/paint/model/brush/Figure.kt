package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

abstract class Figure(
    private val paintInstance: BrushPaint,
) : Brush(paintInstance) {
    override fun updateStyle(paint: Paint) {
        paintInstance.setFigureBrush(paint)
    }

    override fun updateColor(color: Int) {
        paintInstance.color = color
    }

    override fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit
}
