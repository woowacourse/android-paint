package woowacourse.paint.model

import androidx.annotation.DrawableRes
import woowacourse.paint.R

enum class PaintBrush(@DrawableRes val image: Int) {
    PEN(R.drawable.pen),
    RECTANGLE(R.drawable.rectangle),
    CIRCLE(R.drawable.circle),
    ERASER(R.drawable.eraser),
    ;

    companion object {
        fun getPaintBrushes(brushTool: PaintBrush): List<BrushBox> {
            return PaintBrush.values().map {
                BrushBox(it, it == brushTool)
            }
        }
    }
}
