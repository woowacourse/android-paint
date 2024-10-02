package woowacourse.paint

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

data class Drawing(private val path: Path, private val paint: Paint) {
    init {
        setUpDefaultPaint()
    }

    private fun setUpDefaultPaint() {
        paint.apply {
            style = DEFAULT_BRUSH_STYLE
            strokeCap = DEFAULT_BRUSH_CAP
            isAntiAlias = true
        }
    }

    fun drawPath(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    fun pathMoveTo(x: Float, y: Float) {
        path.moveTo(x, y)
    }

    fun pathLineTo(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun copyWithPaint(thickness: Float): Drawing {
        val paint = Paint(paint).apply {
            strokeWidth = thickness
        }
        return Drawing(path, paint)
    }

    fun copyWithPaint(color: Int): Drawing {
        val paint = Paint(paint).apply {
            this.color = color
        }
        return Drawing(path, paint)
    }

    companion object {
        private val DEFAULT_BRUSH_STYLE = Paint.Style.STROKE
        private val DEFAULT_BRUSH_CAP = Paint.Cap.ROUND
    }
}
