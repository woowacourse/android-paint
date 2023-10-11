package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.customview.FreeDrawView

class Rectangle(private val paintInstance: BrushPaint = BrushPaint()) : Figure(paintInstance) {
    private var beforePosition = Pair(0f, 0f)

    override fun onActionMove(xCursor: Float, yCursor: Float) {
        drawPreview(xCursor, yCursor)
    }

    override fun onActionUp(xCursor: Float, yCursor: Float) {
        draw(xCursor, yCursor)
    }

    override fun onActionDown(xCursor: Float, yCursor: Float) {
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
        FreeDrawView.previewDraw = path to paint
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
        FreeDrawView.previousDrawings.add(path to paint)
    }
}
