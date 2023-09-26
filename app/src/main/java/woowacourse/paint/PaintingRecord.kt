package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

data class PaintingRecord(
    val path: Path,
    val brush: Paint
)
