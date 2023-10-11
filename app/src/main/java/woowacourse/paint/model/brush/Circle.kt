package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.customview.FreeDrawView

class Circle(private val paintInstance: BrushPaint = BrushPaint()) : Figure(paintInstance) {
    private var beforePosition = Pair(0f, 0f)
    override fun onActionDown(xCursor: Float, yCursor: Float) {
        beforePosition = xCursor to yCursor
    }

    override fun onActionMove(xCursor: Float, yCursor: Float) {
        drawPreview(xCursor, yCursor)
    }

    override fun onActionUp(xCursor: Float, yCursor: Float) {
        draw(xCursor)
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
