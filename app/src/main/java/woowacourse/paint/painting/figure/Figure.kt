package woowacourse.paint.painting.figure

import android.graphics.Paint
import android.graphics.Path

interface Figure {

    val path: Path

    val paint: Paint

    fun begin(x: Float, y: Float)

    fun extend(x: Float, y: Float)

    fun copy(path: Path): Figure

    fun copy(paint: Paint): Figure
}
