package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

sealed class Brush(
    protected val paintInstance: BrushPaint = BrushPaint(),
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

    fun updateColor(@ColorInt color: Int) {
        paintInstance.color = color
    }

    fun updateThickness(thickness: Float) {
        paintInstance.strokeWidth = thickness
    }

    fun copyPaint(): Paint = Paint().apply { set(paintInstance as Paint) }
}
