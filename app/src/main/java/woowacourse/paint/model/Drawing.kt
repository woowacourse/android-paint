package woowacourse.paint.model

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import kotlin.math.pow
import kotlin.math.sqrt

data class Drawing(
    val path: Path = Path(),
    val paint: Paint = Paint(),
    var brush: Brush = Brush(),
) {
    init {
        updatePaint()
    }

    private fun updatePaint() {
        paint.apply {
            isAntiAlias = true
            color = brush.color
            strokeWidth = brush.strokeWidth
            setPaintStyle()
        }
    }

    private fun setPaintStyle() {
        paint.style = when (brush.brushType) {
            BrushType.PENCIL -> Paint.Style.STROKE
            BrushType.SQUARE -> Paint.Style.FILL
            BrushType.CIRCLE -> Paint.Style.FILL
            BrushType.ERASER -> Paint.Style.STROKE
        }
    }

    fun updateBrushType(brushType: BrushType) {
        brush = brush.changeBrushType(brushType)
        setPaintStyle()
    }

    fun updateBrushWidth(width: Float) {
        brush = brush.changeWidth(width)
        paint.strokeWidth = width
    }

    fun updateBrushColor(color: Int) {
        brush = brush.changeColor(color)
        paint.color = color
    }

    fun move(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
    ) {
        when (brush.brushType) {
            BrushType.PENCIL -> path.lineTo(endX, endY)

            BrushType.SQUARE -> path.addRect(startX, startY, endX, endY, Path.Direction.CW)

            BrushType.CIRCLE -> {
                val radius =
                    sqrt(
                        (endX - startX).toDouble().pow(2.0) +
                                (endY - startY).toDouble().pow(2.0),
                    ).toFloat()
                path.addCircle(startX, startY, radius, Path.Direction.CW)
            }

            BrushType.ERASER -> path.lineTo(endX, endY)
        }
    }

    fun setEraseMode(erase: Boolean) {
        if (erase) {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            paint.xfermode = null
        }
    }
}
