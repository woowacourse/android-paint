package woowacourse.paint.view.model.pen.ink

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Ink(
    val paint: Paint,
    val path: Path
) {
    fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
