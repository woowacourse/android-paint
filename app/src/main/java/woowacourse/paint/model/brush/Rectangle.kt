package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Rectangle(override val paintInstance: Paint = Paint()) : Brush() {
    private var beforePosition = Pair(0f, 0f)
    override fun updateStyle(paint: Paint) {
        paintInstance.set(paint)
        paintInstance.style = Paint.Style.FILL
    }

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        setCurrentPosition(xCursor, yCursor)
    }

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        drawPreview(xCursor, yCursor)
        updateView()
    }

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: () -> Unit) {
        draw(xCursor, yCursor)
        updateView()
    }

    override fun updateColor(color: Int) {
        paintInstance.color = color
    }

    override fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    override fun copyPaint(): Paint = Paint().apply { set(paintInstance) }

    private fun setCurrentPosition(xCursor: Float, yCursor: Float) {
        beforePosition = xCursor to yCursor
    }

    private fun drawPreview(x: Float, y: Float) {
        val path = Path().apply {
            addRect(
                beforePosition.first,
                beforePosition.second,
                x,
                y,
                Path.Direction.CW,
            )
        }
        val paint = Paint().apply { set(paintInstance) }
        previewDraw = path to paint
    }

    private fun draw(xCursor: Float, yCursor: Float) {
        val path = Path().apply {
            addRect(
                beforePosition.first,
                beforePosition.second,
                xCursor,
                yCursor,
                Path.Direction.CCW,
            )
        }

        val paint = Paint().apply { set(paintInstance) }
        previousDrawings.add(path to paint)
    }
}
