package woowacourse.paint.drawingboard

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import woowacourse.paint.BrushType.Companion.DEFAULT_BRUSH_TYPE
import woowacourse.paint.BrushType.Companion.changeBrushType
import woowacourse.paint.Color.Companion.DEFAULT_DRAWING_COLOR
import kotlin.math.max
import kotlin.math.min

class Drawing(val path: Path = Path(), val paint: Paint = Paint()) {
    init {
        initializePaint()
    }

    fun setupDefaultDrawing() {
        paint.apply {
            color = DEFAULT_DRAWING_COLOR
            style = Paint.Style.STROKE
        }
        changeBrushType(DEFAULT_BRUSH_TYPE)
    }

    fun copy() = Drawing(Path(), paint)

    fun moveTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.moveTo(pointX, pointY)
    }

    fun lineTo(
        pointX: Float,
        pointY: Float,
    ) {
        path.lineTo(pointX, pointY)
    }

    fun drawRect(
        startX: Float,
        startY: Float,
        pointX: Float,
        pointY: Float,
    ) {
        path.reset()
        path.addRect(
            min(startX, pointX),
            min(startY, pointY),
            max(startX, pointX),
            max(startY, pointY),
            Path.Direction.CW,
        )
    }

    fun drawCircle(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ) {
        path.reset()
        path.addOval(left, top, right, bottom, Path.Direction.CW)
    }

    fun computeBounds(bounds: RectF) {
        path.computeBounds(bounds, true)
    }

    fun updateStrokeWidth(strokeWidth: Float): Drawing {
        val paint = Paint(paint)
        paint.strokeWidth = strokeWidth
        return Drawing(Path(), paint)
    }

    fun updateColor(color: Int): Drawing {
        val paint = Paint(paint)
        paint.color = color
        return Drawing(Path(), paint)
    }

    fun updatePaintStyle(style: Paint.Style): Drawing {
        val paint = Paint(paint)
        paint.style = style
        return Drawing(Path(), paint)
    }

    private fun initializePaint() =
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
        }
}
