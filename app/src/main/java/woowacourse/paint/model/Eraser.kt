package woowacourse.paint.model

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class Eraser(
    path: Path,
    paint: Paint,
) : Drawing(path, paint) {
    init {
        setPaintStyle()
    }

    override fun setPaintStyle() {
        paint.style = Style.STROKE
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        paint.maskFilter = BlurMaskFilter(3F, BlurMaskFilter.Blur.NORMAL)
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
