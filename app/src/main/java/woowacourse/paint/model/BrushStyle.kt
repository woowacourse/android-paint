package woowacourse.paint.model

import android.graphics.Color

data class BrushStyle(
    val color: Int = Color.RED,
    val strokeWidth: Float = 5f,
    var brushType: BrushType = BrushType.PEN,
)
