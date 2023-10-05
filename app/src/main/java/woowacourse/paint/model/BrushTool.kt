package woowacourse.paint.model

import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class BrushTool(@DrawableRes val image: Int) {
    PEN(R.drawable.pen),
    RECTANGLE(R.drawable.rectangle),
    CIRCLE(R.drawable.circle),
    ERASER(R.drawable.eraser),
    ;

    companion object {
        fun getPaintBrushes(brushTool: BrushTool): List<PaintBrush> {
            return BrushTool.values().map {
                PaintBrush(it, it == brushTool)
            }
        }
    }
}
