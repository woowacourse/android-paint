package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

object Circle : Brush() {
    private var beforePosition = Pair(0f, 0f)
    override fun updateStyle() {
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
        draw(xCursor)
        updateView()
    }

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
        previewDraw = path to paint
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
        previousDrawings.add(path to paint)
    }
}
