package woowacourse.paint.model.brush

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

sealed class Brush {

    abstract fun updateStyle()

    abstract fun onActionDown(xCursor: Float, yCursor: Float, updateView: () -> Unit)

    abstract fun onActionMove(xCursor: Float, yCursor: Float, updateView: () -> Unit)

    abstract fun onActionUp(xCursor: Float, yCursor: Float, updateView: () -> Unit)

    companion object {
        val paintInstance = Paint().apply {
            color = Color.RED
        }
        val previousDrawings: MutableList<Pair<Path, Paint>> = mutableListOf()
        var previewDraw: Pair<Path, Paint> = Pair(Path(), Paint())
    }
}
