package woowacourse.paint.paintingtools

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class Eraser(
    paintColor: Int,
    paintWidth: Float,
) : PaintingTool(
    paintColor = paintColor,
    paintWidth = paintWidth,
) {
    override val paint: Paint = Paint().apply {
        strokeWidth = paintWidth
        style = Paint.Style.STROKE
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun copy(): PaintingTool {
        return Eraser(paintColor, paint.strokeWidth)
    }
}
