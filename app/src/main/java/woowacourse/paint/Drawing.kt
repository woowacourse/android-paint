package woowacourse.paint

import android.graphics.Path


data class Drawing(
    val path: Path,
    val paint: CanvasPaint
)