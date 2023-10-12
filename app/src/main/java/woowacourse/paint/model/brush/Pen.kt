package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Pen(
    private val paintInstance: BrushPaint = BrushPaint(),
) : Brush(
    paintInstance,
) {
    override fun updateStyle(paint: Paint) {
        paintInstance.setPenBrush(paint)
    }

    override fun onActionDown(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        startDraw(xCursor, yCursor)
    }

    override fun onActionMove(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        drawLine(xCursor, yCursor)
        updateView(previewDraw)
    }

    override fun onActionUp(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) {
        updateView(previewDraw)
    }

    override fun updateColor(color: Int) {
        paintInstance.color = color
    }

    override fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    private fun startDraw(x: Float, y: Float) {
        val path = Path().apply { moveTo(x, y) }
        previewDraw = path to Paint(paintInstance)
    }

    private fun drawLine(x: Float, y: Float) {
        previewDraw.first.lineTo(x, y)
    }
}
