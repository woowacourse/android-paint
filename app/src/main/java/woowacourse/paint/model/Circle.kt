package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Path

class Circle(path: Path, paint: Paint) : Drawing(path, paint) {
    init {
        setPaintStyle()
    }

    override fun setPaintStyle() {
        paint.style = Style.FILL
        paint.xfermode = null
        paint.maskFilter = null
    }

    override fun draw(startX: Float, startY: Float, currentX: Float, currentY: Float) {
        drawCircle(startX, startY, currentX, currentY)
    }

    private fun drawCircle(
        startX: Float,
        startY: Float,
        currentX: Float,
        currentY: Float,
    ) {
        path.reset()
        path.addOval(
            startX,
            startY,
            currentX,
            currentY,
            Path.Direction.CW,
        )
    }
}
