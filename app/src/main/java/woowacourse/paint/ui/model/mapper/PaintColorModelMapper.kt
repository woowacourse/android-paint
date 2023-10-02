package woowacourse.paint.ui.model.mapper

import android.graphics.Color
import woowacourse.paint.model.PaintColor
import woowacourse.paint.ui.model.PaintColorModel

fun PaintColor.toPaintColorModel(isSelected: Boolean): PaintColorModel {
    return PaintColorModel(Color.parseColor(color), isSelected)
}
