package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Rectangle : Figure() {
    private var beforePosition = Pair(0f, 0f)

    override fun onActionMove(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        draw(xCursor, yCursor)
        updateView(previewDraw)
    }

    override fun onActionUp(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        draw(xCursor, yCursor)
        updateView(previewDraw)
    }

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) {
        beforePosition = xCursor to yCursor
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
        previewDraw = path to paint
    }
}
