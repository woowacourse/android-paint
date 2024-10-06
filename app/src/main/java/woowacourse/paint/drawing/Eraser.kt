package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import woowacourse.paint.drawing.Drawing.Companion.DEFAULT_BRUSH_CAP
import woowacourse.paint.drawing.Drawing.Companion.DEFAULT_BRUSH_STYLE

class Eraser : Drawing {
    val pen = Pen.default().copy(
        paint = Paint().apply {
            style = DEFAULT_BRUSH_STYLE
            strokeCap = DEFAULT_BRUSH_CAP
            isAntiAlias = true
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }
    )

    override fun drawOn(canvas: Canvas) {
        pen.drawOn(canvas)
    }

    override fun setStartPoint(x: Float, y: Float) {
        pen.setStartPoint(x, y)
    }

    override fun pathLineTo(x: Float, y: Float) {
        pen.pathLineTo(x, y)
    }

    override fun copyWithPaint(thickness: Float): Drawing = pen.copyWithPaint(thickness)

    override fun copyWithPaint(color: Int): Drawing = pen.copyWithPaint(color)

    override fun copyPoint(pointX: Float, pointY: Float): Drawing = pen.copyPoint(pointX, pointY)
}
