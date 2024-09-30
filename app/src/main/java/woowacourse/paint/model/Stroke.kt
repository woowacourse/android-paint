package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path

data class Stroke(
    val path: Path,
    val paint: Paint
)
