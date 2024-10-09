package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Path

class Pen(
    path: Path,
    paint: Paint,
) : Drawing(path, paint) {
    init {
        setPaintStyle()
    }

    override fun setPaintStyle() {
        paint.style = Style.STROKE
        paint.xfermode = null
        paint.maskFilter = null
    }

    override fun draw(startX: Float, startY: Float, currentX: Float, currentY: Float) {
        drawLine(currentX, currentY)
    }

    private fun drawLine(
        currentX: Float,
        currentY: Float,
    ) {
        path.lineTo(currentX, currentY)
    }
}
