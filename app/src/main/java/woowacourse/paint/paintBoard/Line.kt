package woowacourse.paint.paintBoard

import android.graphics.Path

data class Line(
    val path: Path = Path(),
    val brush: Brush = Brush()
)