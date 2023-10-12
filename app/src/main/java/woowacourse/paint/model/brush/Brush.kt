package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

sealed class Brush(
    private val paintInstance: BrushPaint,
) {
    var previewDraw: Pair<Path, Paint> = Pair(Path(), Paint())
    abstract fun updateStyle(paint: Paint)

    abstract fun onActionDown(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    )

    abstract fun onActionMove(
        xCursor: Float,
        yCursor: Float,
        updateView: (Pair<Path, Paint>) -> Unit,
    )

    abstract fun onActionUp(xCursor: Float, yCursor: Float, updateView: (Pair<Path, Paint>) -> Unit)

    abstract fun updateColor(@ColorInt color: Int)

    abstract fun updateThickness(thickness: Float)
    fun copyPaint(): Paint = Paint().apply { set(paintInstance as Paint) }
}
