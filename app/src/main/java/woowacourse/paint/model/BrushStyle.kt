package woowacourse.paint.model

import android.graphics.Color

data class BrushStyle(
    val color: Int = Color.RED,
    val strokeWidth: Float = 5f,
    val brushType: BrushType = BrushType.PEN,
)
