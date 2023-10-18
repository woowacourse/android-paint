package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

abstract class Figure : Brush() {

    override fun updateStyle(paint: Paint) {
        super.updateStyle(paint)
        paintInstance.style = Paint.Style.FILL
    }

    override fun onActionUp(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) = Unit

    override fun onActionDown(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) = Unit

    override fun onActionMove(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    ) = Unit

    private fun setFigureBrush(beforePaint: Paint) = paintInstance.apply {

    }
}
