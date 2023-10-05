package woowacourse.paint.model.line

import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path,
    val paint: Paint,
) {
    constructor(paint: Paint) : this(Path(), paint)
    constructor() : this(Path(), Paint())
}
