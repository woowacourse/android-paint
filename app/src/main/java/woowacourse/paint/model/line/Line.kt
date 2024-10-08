package woowacourse.paint.model.line

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.model.Sketch

data class Line(
    val path: Path,
    val color: Int,
    val strokeWidth: Float,
) : Sketch(color, strokeWidth) {
    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun isTouched(
        x: Float,
        y: Float,
    ): Boolean {
        val bounds = RectF()
        path.computeBounds(bounds, true)
        return bounds.contains(x, y)
    }
}
