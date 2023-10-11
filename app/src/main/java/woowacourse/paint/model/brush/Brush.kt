package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.ColorInt

sealed class Brush {
    abstract val paintInstance: Paint
    abstract fun updateStyle(paint: Paint)

    abstract fun onActionDown(xCursor: Float, yCursor: Float)

    abstract fun onActionMove(xCursor: Float, yCursor: Float)

    abstract fun onActionUp(xCursor: Float, yCursor: Float)

    abstract fun updateColor(@ColorInt color: Int)

    abstract fun updateThickness(thickness: Float)

    companion object {
        val previousDrawings: MutableList<Pair<Path, Paint>> = mutableListOf()
        var previewDraw: Pair<Path, Paint> = Pair(Path(), Paint())
    }

    abstract fun copyPaint(): Paint
}
