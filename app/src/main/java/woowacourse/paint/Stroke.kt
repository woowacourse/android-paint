package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

data class Stroke(
    val path: Path?,
    val paint: Paint,
    val brushType: BrushType,
    val startX: Float = 0f,
    val startY: Float = 0f,
    val endX: Float = 0f,
    val endY: Float = 0f,
)
