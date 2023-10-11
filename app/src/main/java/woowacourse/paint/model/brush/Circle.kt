package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.customview.FreeDrawView

class Circle(override val paintInstance: Paint = Paint()) : Brush() {
    private var beforePosition = Pair(0f, 0f)
    override fun updateStyle(paint: Paint) {
        paintInstance.set(paint)
        paintInstance.style = Paint.Style.FILL
    }

    override fun onActionDown(xCursor: Float, yCursor: Float) {
        setCurrentPosition(xCursor, yCursor)
    }

    override fun onActionMove(xCursor: Float, yCursor: Float) {
        drawPreview(xCursor, yCursor)
    }

    override fun onActionUp(xCursor: Float, yCursor: Float) {
        draw(xCursor)
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

    private fun drawPreview(xCursor: Float, yCursor: Float) {
        val path = Path().apply {
            addCircle(
                beforePosition.first + ((xCursor - beforePosition.first) / 2),
                beforePosition.second + ((xCursor - beforePosition.first) / 2),
                (xCursor - beforePosition.first) / 2,
                Path.Direction.CW,
            )
        }
        val paint = Paint().apply { set(paintInstance) }
        FreeDrawView.previewDraw = path to paint
    }

    private fun draw(xCursor: Float) {
        val path = Path().apply {
            addCircle(
                beforePosition.first + ((xCursor - beforePosition.first) / 2),
                beforePosition.second + ((xCursor - beforePosition.first) / 2),
                (xCursor - beforePosition.first) / 2,
                Path.Direction.CW,
            )
        }
        val paint = Paint().apply { set(paintInstance) }
        FreeDrawView.previousDrawings.add(path to paint)
    }
}
