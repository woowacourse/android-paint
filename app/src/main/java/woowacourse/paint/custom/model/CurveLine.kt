package woowacourse.paint.custom.model

import android.graphics.Paint
import android.graphics.Path

data class CurveLine(
    val path: Path,
    val paint: Paint,
) {
    init {
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
    }
}
