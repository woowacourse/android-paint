package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Circle : Figure() {
    private var beforePosition = Pair(0f, 0f)

    override fun onActionDown(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        beforePosition = xCursor to yCursor
    }

    override fun onActionMove(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        drawPreview(xCursor, yCursor)
        updateView(previewDraw)
    }

    override fun onActionUp(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        draw(xCursor)
        updateView(previewDraw)
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
        previewDraw = path to paint
    }
}
