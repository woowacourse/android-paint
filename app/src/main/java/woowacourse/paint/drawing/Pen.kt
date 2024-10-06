package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.drawing.Drawing2.Companion.DEFAULT_BRUSH_CAP
import woowacourse.paint.drawing.Drawing2.Companion.DEFAULT_BRUSH_STYLE

class Pen(private val path: Path, private val paint: Paint) : Drawing2 {
    override fun setUpDefaultPaint() {
        paint.apply {
            style = DEFAULT_BRUSH_STYLE
            strokeCap = DEFAULT_BRUSH_CAP
            isAntiAlias = true
        }
    }

    override fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setStartPoint(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    override fun pathLineTo(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    override fun copyWithPaint(thickness: Float): Drawing2 {
        val paint =
            Paint(paint).apply {
                strokeWidth = thickness
            }
        return Pen(path, paint)
    }

    override fun copyWithPaint(color: Int): Drawing2 {
        val paint =
            Paint(paint).apply {
                this.color = color
            }
        return Pen(path, paint)
    }

}

