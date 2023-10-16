package woowacourse.paint.paintboard.common

import android.graphics.Paint
import androidx.annotation.ColorInt

sealed interface Brush {
    data class LineBrush(
        @ColorInt val color: Int,
        val width: Int,
    ) : Brush

    data class EraserBrush(
        val width: Int,
        val mode: EraserState,
    ) : Brush

    data class ShapeBrush(
        @ColorInt val color: Int,
        val width: Int,
        val fillType: Paint.Style,
        val mode: Shape,
    ) : Brush
}
