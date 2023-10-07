package woowacourse.paint.painting

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class Eraser(
    path: Path = Path(),
    paintColor: Int,
    paintWidth: Float,
) : Painting(
    path = path,
    paint = Paint().apply {
        color = paintColor
        strokeWidth = paintWidth
        style = Paint.Style.STROKE
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    },
) {
    override fun onActionDown(x: Float, y: Float) {
        path.moveTo(x, y)
        path.lineTo(x, y)
    }

    override fun onActionMove(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun getInitializedPathPainting(): Painting {
        return Eraser(path = Path(), paintColor = paint.color, paintWidth = paint.strokeWidth)
    }

    override fun drawOnCanvas(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
