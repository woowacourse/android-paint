package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.drawing.Drawing.Companion.DEFAULT_BRUSH_CAP
import woowacourse.paint.drawing.Drawing.Companion.DEFAULT_BRUSH_STYLE

data class Pen(private val path: Path, private val paint: Paint) : Drawing {
    override fun drawOn(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setStartPoint(
        x: Float,
        y: Float,
    ) {
        path.moveTo(x, y)
    }

    override fun pathLineTo(
        x: Float,
        y: Float,
    ) {
        path.lineTo(x, y)
    }

    override fun copyWithPaint(thickness: Float): Drawing {
        val paint =
            Paint(paint).apply {
                strokeWidth = thickness
            }
        return Pen(path, paint)
    }

    override fun copyWithPaint(color: Int): Drawing {
        val paint =
            Paint(paint).apply {
                this.color = color
            }
        return Pen(path, paint)
    }

    override fun copyPoint(
        pointX: Float,
        pointY: Float,
    ): Drawing = this.copy(path = Path())

    companion object {
        fun default(): Pen {
            val paint =
                Paint().apply {
                    style = DEFAULT_BRUSH_STYLE
                    strokeCap = DEFAULT_BRUSH_CAP
                    isAntiAlias = true
                }
            return Pen(Path(), paint)
        }
    }
}
