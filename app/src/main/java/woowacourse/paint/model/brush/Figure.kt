package woowacourse.paint.model.brush

import android.graphics.Paint

abstract class Figure(private val paintInstance: BrushPaint) : Brush(paintInstance) {
    override fun updateStyle(paint: Paint) {
        paintInstance.set(paint)
        paintInstance.style = Paint.Style.FILL
    }

    override fun updateColor(color: Int) {
        paintInstance.color = color
    }

    override fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    override fun onActionUp(xCursor: Float, yCursor: Float) = Unit

    override fun onActionDown(xCursor: Float, yCursor: Float) = Unit

    override fun onActionMove(xCursor: Float, yCursor: Float) = Unit
}
