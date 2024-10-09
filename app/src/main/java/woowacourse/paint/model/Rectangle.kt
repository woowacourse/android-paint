package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Path
import kotlin.math.max
import kotlin.math.min

class Rectangle(
    path: Path,
    paint: Paint,
) : Drawing(path, paint) {
    init {
        setPaintStyle()
    }

    override fun setPaintStyle() {
        paint.style = Style.FILL
        paint.xfermode = null
        paint.maskFilter = null
    }

    override fun draw(startX: Float, startY: Float, currentX: Float, currentY: Float) {
        drawRectangle(startX, startY, currentX, currentY)
    }

    private fun drawRectangle(
        startX: Float,
        startY: Float,
        currentX: Float,
        currentY: Float,
    ) {
        path.reset()
        path.addRect(
            min(startX, currentX),
            min(startY, currentY),
            max(currentX, startX),
            max(currentY, startY),
            Path.Direction.CW,
        )
    }
}
