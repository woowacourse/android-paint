package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

class Pen() : Brush() {
    override fun updateStyle(paint: Paint) {
        setPenBrush(paint)
    }

    private fun setFigureBrush(beforePaint: Paint) = paintInstance.apply {
        set(beforePaint)
        style = Paint.Style.FILL
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

    private fun startDraw(x: Float, y: Float) {
        val path = Path().apply { moveTo(x, y) }
        previewDraw = path to Paint(paintInstance)
    }

    private fun drawLine(x: Float, y: Float) {
        previewDraw.first.lineTo(x, y)
    }

    private fun setPenBrush(beforePaint: Paint) = paintInstance.apply {
        set(beforePaint)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
}
