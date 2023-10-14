package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

abstract class Figure : Brush() {
    override fun updateStyle(paint: Paint) {
        paintInstance.setFigureBrush(paint)
    }

    override fun onActionUp(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit

    override fun onActionDown(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit

    override fun onActionMove(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit) = Unit
}
