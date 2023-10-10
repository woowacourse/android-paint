package woowacourse.paint.custom.view.model

import android.graphics.Paint
import android.graphics.Path

data class Line(
    val path: Path,
    val paint: Paint,
) {
    private var lastX = 0f
    private var lastY = 0f

    fun moveTo(x: Float, y: Float) {
        path.moveTo(x, y)
        lastX = x
        lastY = y
    }

    fun quadTo(x: Float, y: Float) {
        path.quadTo(lastX, lastY, (x + lastX) / 2, (y + lastY) / 2)
        lastX = x
        lastY = y
    }
}
