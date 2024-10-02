package woowacourse.paint

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

data class Drawing(val path: Path, val paint: Paint) {
    init {
        setUpDefaultPaint()
    }

    private fun setUpDefaultPaint() {
        paint.apply {
            color = DEFAULT_BRUSH_COLOR
            style = DEFAULT_BRUSH_STYLE
            strokeCap = DEFAULT_BRUSH_CAP
            isAntiAlias = true
        }
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
        private const val DEFAULT_BRUSH_COLOR = Color.BLACK
        private val DEFAULT_BRUSH_STYLE = Paint.Style.STROKE
        private val DEFAULT_BRUSH_CAP = Paint.Cap.ROUND
    }
}
